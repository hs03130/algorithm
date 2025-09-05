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
	static long[] dp;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		dp = new long[N + 1];

		dp[0] = 0;
		dp[1] = 1;

		for (int idx = 2; idx <= N; idx++) {
			dp[idx] = dp[idx - 2] + dp[idx - 1];
		}

		sb.append(dp[N]);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}