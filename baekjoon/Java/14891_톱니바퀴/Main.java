import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int cmdCnt;
	static ArrayList<Integer>[] gears;
	static int ans = 0;

	static final int GEAR_CNT = 4, GEAR = 0, DIR = 1;
	static final int N = 0, S = 1;

	public static void main(String[] args) throws IOException {
		// 입력
		gears = new ArrayList[GEAR_CNT];
		for (int row = 0; row < GEAR_CNT; row++) {
			String line = br.readLine();
			gears[row] = new ArrayList<>();
			for (int col = 0; col < 8; col++) {
				gears[row].add(line.charAt(col) - '0');
			}
		}

		// 톱니바퀴 회전하기
		cmdCnt = Integer.parseInt(br.readLine());
		for (int cmdIdx = 0; cmdIdx < cmdCnt; cmdIdx++) {
			st = new StringTokenizer(br.readLine());
			int gear = Integer.parseInt(st.nextToken()) - 1;
			int direction = Integer.parseInt(st.nextToken());

			// 왼쪽 기어를 움직일지 확인
			checkLeft(gear, direction);

			// 움직인 기어 다시 복구
			rotate(gear, direction * -1);

			// 오른쪽 기어를 움직일지 확인
			checkRight(gear, direction);
		}

		// 결과 확인
		for (int idx = 0, score = 1; idx < GEAR_CNT; idx++, score *= 2) {
			ans += gears[idx].get(0) == N ? 0 : score;
		}
		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void checkLeft(int curGearIdx, int rotateDir) {
		if (curGearIdx == 0) {
			rotate(curGearIdx, rotateDir);
			return;
		}

		// 왼쪽 기어의 2번째 톱니와 현재 기어의 6번째 톱니가 다르면
		if (gears[curGearIdx].get(6) != gears[curGearIdx - 1].get(2)) {
			checkLeft(curGearIdx - 1, rotateDir * -1);
		}

		rotate(curGearIdx, rotateDir);
	}

	static void checkRight(int curGearIdx, int rotateDir) {
		if (curGearIdx == GEAR_CNT - 1) {
			rotate(curGearIdx, rotateDir);
			return;
		}

		// 오른쪽 기어의 6번째 톱니와 현재 기어의 2번째 톱니가 다르면
		if (gears[curGearIdx].get(2) != gears[curGearIdx + 1].get(6)) {
			checkRight(curGearIdx + 1, rotateDir * -1);
		}

		rotate(curGearIdx, rotateDir);
	}

	static void rotate(int gearIdx, int rotateDir) {
		// 시계방향
		if (rotateDir == 1) {
			gears[gearIdx].add(0, gears[gearIdx].remove(7));
		}
		// 반시계방향
		else {
			gears[gearIdx].add(7, gears[gearIdx].remove(0));
		}
	}
}
