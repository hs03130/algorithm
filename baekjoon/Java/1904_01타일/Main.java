import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static int len;
	static int[] dp;
	
	static final int MOD = 15746;

	public static void main(String[] args) throws IOException {
		len = Integer.parseInt(br.readLine());
		dp = new int[1000001];
		dp[1] = 1;
		dp[2] = 2;
		for (int idx = 3; idx <= len; idx++) {
			dp[idx] = (dp[idx - 2] + dp[idx - 1]) % MOD;
		}

		sb.append(dp[len]);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}