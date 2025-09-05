import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int totalTime, moveCnt;
	static int[] tree;
	static int[][] dp;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		totalTime = Integer.parseInt(st.nextToken());
		moveCnt = Integer.parseInt(st.nextToken());
		tree = new int[totalTime];
		for (int idx = 0; idx < totalTime; idx++) {
			tree[idx] = Integer.parseInt(br.readLine());
		}

		dp = new int[totalTime][moveCnt + 1];
		if (tree[0] == 1) {
			dp[0][0] = 1;
		} else {
			dp[0][1] = 1;
		}
		for (int time = 1; time < totalTime; time++) {
			// move : 0, 2, 4, ... -> 1번 나무
			// move : 1, 3, 5, ... -> 2번 나무
			for (int move = 0; move <= moveCnt; move++) {
				if (tree[time] % 2 != move % 2) { // 같은 나무
					// 움직이지 않고 하나 먹는다
					dp[time][move] = Math.max(dp[time][move], dp[time - 1][move] + 1);
				} else { // 다른 나무
					// 움직여서 먹는다
					if (move != moveCnt) {
						dp[time][move + 1] = Math.max(dp[time][move + 1], dp[time - 1][move] + 1);
					}

					// 움직이지 않고 먹지 않는다
					dp[time][move] = Math.max(dp[time][move], dp[time - 1][move]);
				}
			}

		}

		for (int move = 0; move <= moveCnt; move++) {
			if (dp[totalTime - 1][move] > ans) {
				ans = dp[totalTime - 1][move];
			}
		}
		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}