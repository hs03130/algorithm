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

		dp[0] = nums[0];
		ans = dp[0];
		for (int idx = 1; idx < N; idx++) {
			if (dp[idx - 1] + nums[idx] > nums[idx]) {
				dp[idx] = dp[idx - 1] + nums[idx];
			} else {
				dp[idx] = nums[idx];
			}

			if (dp[idx] > ans) {
				ans = dp[idx];
			}
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}