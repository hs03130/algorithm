import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[] nums;
	static int[][] dp;

	static final int DRINK = 0, KEEP = 1;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		dp = new int[N][2];
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(br.readLine());
		}

		dp[0][DRINK] = nums[0];
		if (N > 1) {
			dp[1][DRINK] = nums[0] + nums[1];
			dp[1][KEEP] = nums[0];
		}

		for (int idx = 2; idx < N; idx++) {
			// 현재 잔을 마시려면 두번째 전 잔을 마시지 않거나 첫번째 전 잔을 마시지 않아야함
			dp[idx][DRINK] = Math.max(dp[idx - 2][KEEP] + nums[idx - 1], dp[idx - 2][DRINK]) + nums[idx];

			// 현재 잔을 마시지 않으면 이전 상황에서 큰 것을 선택
			dp[idx][KEEP] = Math.max(dp[idx - 1][DRINK], dp[idx - 1][KEEP]);
		}

		sb.append(Math.max(dp[N - 1][DRINK], dp[N - 1][KEEP]));

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}