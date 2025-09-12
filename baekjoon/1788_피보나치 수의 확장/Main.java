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
	static int[] dp;

	static final int MOD = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		dp = new int[1000001];

		dp[0] = 0;
		dp[1] = 1;
		for (int idx = 2; idx <= Math.abs(N); idx++) {
			dp[idx] = (dp[idx - 1] + dp[idx - 2]) % MOD;
		}

		if (N < 0 && N % 2 == 0) {
			sb.append(-1 + "\n");
		} else if (N == 0) {
			sb.append(0 + "\n");
		} else {
			sb.append(1 + "\n");
		}
		sb.append(dp[Math.abs(N)]);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}