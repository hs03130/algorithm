import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static int[] dp, pre;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());
		dp = new int[1000001];
		pre = new int[1000001];

		dp[1] = 0;
		for (int idx = 2; idx <= N; idx++) {
			dp[idx] = dp[idx - 1] + 1;
			pre[idx] = idx - 1;

			if (idx % 2 == 0 && dp[idx] > dp[idx / 2] + 1) {
				dp[idx] = dp[idx / 2] + 1;
				pre[idx] = idx / 2;
			}

			if (idx % 3 == 0 && dp[idx] > dp[idx / 3] + 1) {
				dp[idx] = dp[idx / 3] + 1;
				pre[idx] = idx / 3;
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(dp[N] + "\n");
		
		for (int idx = N; idx > 1;) {
			sb.append(idx + " ");
			idx = pre[idx];
		}
		sb.append(1);

		System.out.println(sb);
	}

}
