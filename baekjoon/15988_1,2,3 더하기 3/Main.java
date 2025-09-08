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

	static final int MOD = 1_000_000_009;

	public static void main(String[] args) throws IOException {
		dp = new int[1000001];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		for (int num = 4; num <= 1000000; num++) {
			for (int k = 1; k <= 3; k++) {
				dp[num] = (dp[num] + dp[num - k]) % MOD;
			}
		}

		N = Integer.parseInt(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			sb.append(dp[Integer.parseInt(br.readLine())] + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}