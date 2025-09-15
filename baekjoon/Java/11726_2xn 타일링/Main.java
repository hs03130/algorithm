import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int num;
	static int[] dp;
	static int ans;
	static final int MOD = 10007;

	// ют╥б
	static void input() throws IOException {
		num = Integer.parseInt(br.readLine().trim());
	}

	static void solve() {
		if (num == 1) {
			ans = 1;
		} else if (num == 2) {
			ans = 2;
		} else {
			dp = new int[num];
			dp[1] = 1;
			dp[2] = 2;
			for (int idx = 3; idx < num; idx++) {
				dp[idx] = (dp[idx - 1] + dp[idx - 2]) % MOD;
			}
			ans = (dp[num - 1] + dp[num - 2]) % MOD;
		}
	}

	public static void main(String args[]) throws IOException {
		input();
		solve();
		System.out.println(ans);
	}

}