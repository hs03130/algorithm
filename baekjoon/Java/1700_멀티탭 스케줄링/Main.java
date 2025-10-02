import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int plugSize, useCnt;
	static HashSet<Integer> powerStrip; // 멀티탭에 꽂혀있는 장치 번호
	static int[] schedule; // 전체 순서
	static boolean[] isUsed; // 기기가 꽂혀있는지 여부
	static ArrayList<ArrayList<Integer>> nextUseIdxs; // 기기별 다음 사용 순서
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		plugSize = Integer.parseInt(st.nextToken());
		useCnt = Integer.parseInt(st.nextToken());

		schedule = new int[useCnt];
		nextUseIdxs = new ArrayList<>(); // 1-based
		for (int idx = 0; idx < 101; idx++) {
			nextUseIdxs.add(new ArrayList<>());
		}

		// 사용 순서
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < useCnt; idx++) {
			int num = Integer.parseInt(st.nextToken());
			schedule[idx] = num;
			nextUseIdxs.get(num).add(idx);
		}

		// 빼는 횟수
		powerStrip = new HashSet<>();
		isUsed = new boolean[101]; // 1-based
		for (int idx = 0; idx < useCnt; idx++) {
			int curDevice = schedule[idx];
			nextUseIdxs.get(curDevice).remove(0);

			// 꽂아야 하는데 멀티탭에 자리 없음
			if (!isUsed[curDevice] && powerStrip.size() >= plugSize) {
				// 가장 나중에 사용되는 기기 제거
				int maxIdx = -1, removeDevice = 0;
				for (int num : powerStrip) {
					int nextIdx = nextUseIdxs.get(num).isEmpty() ? Integer.MAX_VALUE : nextUseIdxs.get(num).get(0);
					if (nextIdx > maxIdx) {
						removeDevice = num;
						maxIdx = nextIdx;
					}
				}
				powerStrip.remove(removeDevice);
				isUsed[removeDevice] = false;
				ans++;
			}

			// 새로운 기기 꽂기
			powerStrip.add(curDevice);
			isUsed[curDevice] = true;
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
