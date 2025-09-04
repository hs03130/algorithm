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
	static long[][] dp; // N자리에서 k로 끝나는 수 개수
	static long ans = 0;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		dp = new long[N + 1][10];

		for (int idx = 1; idx < 10; idx++) {
			dp[1][idx] = 1;
		}

		for (int n = 2; n <= N; n++) {
			for (int k = 0; k < 10; k++) {
				if (k != 0) {
					dp[n][k] += dp[n - 1][k - 1];
				}
				if (k != 9) {
					dp[n][k] += dp[n - 1][k + 1];
				}

				dp[n][k] %= 1_000_000_000;
			}
		}

		for (int idx = 0; idx < 10; idx++) {
			ans += dp[N][idx];
			ans %= 1_000_000_000;
		}
		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}