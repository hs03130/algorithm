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
		// �Է�
		gears = new ArrayList[GEAR_CNT];
		for (int row = 0; row < GEAR_CNT; row++) {
			String line = br.readLine();
			gears[row] = new ArrayList<>();
			for (int col = 0; col < 8; col++) {
				gears[row].add(line.charAt(col) - '0');
			}
		}

		// ��Ϲ��� ȸ���ϱ�
		cmdCnt = Integer.parseInt(br.readLine());
		for (int cmdIdx = 0; cmdIdx < cmdCnt; cmdIdx++) {
			st = new StringTokenizer(br.readLine());
			int gear = Integer.parseInt(st.nextToken()) - 1;
			int direction = Integer.parseInt(st.nextToken());

			// ���� �� �������� Ȯ��
			checkLeft(gear, direction);

			// ������ ��� �ٽ� ����
			rotate(gear, direction * -1);

			// ������ �� �������� Ȯ��
			checkRight(gear, direction);
		}

		// ��� Ȯ��
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

		// ���� ����� 2��° ��Ͽ� ���� ����� 6��° ��ϰ� �ٸ���
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

		// ������ ����� 6��° ��Ͽ� ���� ����� 2��° ��ϰ� �ٸ���
		if (gears[curGearIdx].get(2) != gears[curGearIdx + 1].get(6)) {
			checkRight(curGearIdx + 1, rotateDir * -1);
		}

		rotate(curGearIdx, rotateDir);
	}

	static void rotate(int gearIdx, int rotateDir) {
		// �ð����
		if (rotateDir == 1) {
			gears[gearIdx].add(0, gears[gearIdx].remove(7));
		}
		// �ݽð����
		else {
			gears[gearIdx].add(7, gears[gearIdx].remove(0));
		}
	}
}
