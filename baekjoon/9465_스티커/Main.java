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

	static int testCase;
	static int N;
	static int[][] stickers;
	static int[][] dp;
	static int ans;

	static final int UP = 0, DOWN = 1, NONE = 2;

	public static void main(String[] args) throws IOException {
		testCase = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			stickers = new int[N][2];
			dp = new int[N][3];

			for (int row = 0; row < 2; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < N; col++) {
					stickers[col][row] = Integer.parseInt(st.nextToken());
				}
			}

			dp[0][UP] = stickers[0][UP];
			dp[0][DOWN] = stickers[0][DOWN];
			ans = Math.max(dp[0][UP], dp[0][DOWN]);

			for (int idx = 1; idx < N; idx++) {
				// UP
				dp[idx][UP] = Math.max(dp[idx - 1][DOWN], dp[idx - 1][NONE]) + stickers[idx][UP];

				// DOWN
				dp[idx][DOWN] = Math.max(dp[idx - 1][UP], dp[idx - 1][NONE]) + stickers[idx][DOWN];

				// NONE
				dp[idx][NONE] = Math.max(dp[idx - 1][NONE], Math.max(dp[idx - 1][UP], dp[idx - 1][DOWN]));

				if (ans < Math.max(dp[idx][NONE], Math.max(dp[idx][UP], dp[idx][DOWN]))) {
					ans = Math.max(dp[idx][NONE], Math.max(dp[idx][UP], dp[idx][DOWN]));
				}
			}

			sb.append(ans + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}