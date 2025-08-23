import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int homeCnt;
	static int[][] price;
	static int[][] dp;
	static int ans;

	static final int RED = 0, GREEN = 1, BLUE = 2;

	public static void main(String[] args) throws IOException {
		homeCnt = Integer.parseInt(br.readLine());

		price = new int[homeCnt][3];
		dp = new int[homeCnt][3];

		for (int idx = 0; idx < homeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			price[idx][RED] = Integer.parseInt(st.nextToken());
			price[idx][GREEN] = Integer.parseInt(st.nextToken());
			price[idx][BLUE] = Integer.parseInt(st.nextToken());
		}

		dp[0][RED] = price[0][RED];
		dp[0][GREEN] = price[0][GREEN];
		dp[0][BLUE] = price[0][BLUE];

		for (int idx = 1; idx < homeCnt; idx++) {
			dp[idx][RED] = Math.min(dp[idx - 1][GREEN], dp[idx - 1][BLUE]) + price[idx][RED];
			dp[idx][GREEN] = Math.min(dp[idx - 1][RED], dp[idx - 1][BLUE]) + price[idx][GREEN];
			dp[idx][BLUE] = Math.min(dp[idx - 1][RED], dp[idx - 1][GREEN]) + price[idx][BLUE];
		}

		ans = Math.min(dp[homeCnt - 1][RED], dp[homeCnt - 1][GREEN]);
		ans = Math.min(ans, dp[homeCnt - 1][BLUE]);

		System.out.println(ans);
	}

}
