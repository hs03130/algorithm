import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int h;
	static int[][] board;
	static int[][] dp;
	static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {

		h = Integer.parseInt(br.readLine());

		board = new int[h][];
		dp = new int[h][];
		for (int row = 0; row < h; row++) {
			board[row] = new int[row + 1];
			dp[row] = new int[row + 1];
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < row + 1; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		dp[0][0] = board[0][0];
		for (int row = 0; row < h - 1; row++) {
			for (int col = 0; col < row + 1; col++) {
				dp[row + 1][col] = Math.max(dp[row + 1][col], dp[row][col] + board[row + 1][col]);
				dp[row + 1][col + 1] = Math.max(dp[row + 1][col + 1], dp[row][col] + board[row + 1][col + 1]);
			}
		}

		for (int idx = 0; idx < h; idx++) {
			if (ans < dp[h - 1][idx])
				ans = dp[h - 1][idx];
		}
		System.out.println(ans);
	}

}
