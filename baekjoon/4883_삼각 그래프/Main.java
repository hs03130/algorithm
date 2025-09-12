/**
 * 그래프를 보면 같은 줄에서도 오른쪽으로 이동 가능
 * 정점 비용은 음수일 수 있음
 */
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

	static int testCase = 1;
	static int N;
	static int[][] dp;

	static final int LEFT = 0, MID = 1, RIGHT = 2;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		while (N != 0) {
			dp = new int[N][3];

			st = new StringTokenizer(br.readLine());

			// 첫번째줄 LEFT로는 갈 수 없음. 오른쪽으로 이동
			st.nextToken();
			dp[0][LEFT] = Integer.MAX_VALUE;
			dp[0][MID] = Integer.parseInt(st.nextToken());
			dp[0][RIGHT] = Integer.parseInt(st.nextToken()) + dp[0][MID];

			for (int row = 1; row < N; row++) {
				st = new StringTokenizer(br.readLine());

				// left
				dp[row][LEFT] = Math.min(dp[row - 1][LEFT], dp[row - 1][MID]) + Integer.parseInt(st.nextToken());

				// mid
				dp[row][MID] = Math.min(Math.min(dp[row][LEFT], dp[row - 1][LEFT]),
						Math.min(dp[row - 1][MID], dp[row - 1][RIGHT])) + Integer.parseInt(st.nextToken());

				// right
				dp[row][RIGHT] = Math.min(Math.min(dp[row][MID], dp[row - 1][MID]), dp[row - 1][RIGHT])
						+ Integer.parseInt(st.nextToken());
			}

			sb.append(testCase + ". " + dp[N - 1][MID] + "\n");

			testCase++;
			N = Integer.parseInt(br.readLine());
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}