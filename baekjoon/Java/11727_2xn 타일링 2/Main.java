import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static int[] dp;
	static int ans;

	public static void main(String[] args) throws Exception {
		// 입력
		n = Integer.parseInt(br.readLine());

		// 풀이
		if (n == 1) {
			ans = 1;
		} else if (n == 2) {
			ans = 3;
		} else {
			dp = new int[n + 1];
			dp[0] = 0;
			dp[1] = 1;
			dp[2] = 3;

			for (int idx = 3; idx <= n; idx++) {
				dp[idx] = (dp[idx - 1] % 10007 + dp[idx - 2] * 2 % 10007) % 10007;
			}

			ans = dp[n];
		}

		// 출력
		System.out.println(ans);
	}

}