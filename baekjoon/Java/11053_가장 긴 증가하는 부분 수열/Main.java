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

	static int N;
	static int[] nums;
	static int[] dp;
	static int ans;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		dp = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(st.nextToken());
		}

		dp[0] = 1;
		ans = 1;
		for (int n = 1; n < N; n++) {
			dp[n] = 1;
			for (int idx = 0; idx < n; idx++) {
				if (nums[idx] < nums[n]) {
					dp[n] = Math.max(dp[idx] + 1, dp[n]);
				}
			}
			if (dp[n] > ans) {
				ans = dp[n];
			}
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}