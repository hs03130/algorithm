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
	static int[] cards;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		cards = new int[N + 1];
		dp = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int idx = 1; idx <= N; idx++) {
			cards[idx] = Integer.parseInt(st.nextToken());
		}
		dp[1] = cards[1];
		for(int n=2; n<=N; n++) {
			dp[n] = cards[n];
			for(int cnt=1; cnt<n; cnt++) {
				dp[n] = Math.max(dp[n], dp[n-cnt] + dp[cnt]);
			}
		}
		
		sb.append(dp[N]);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}