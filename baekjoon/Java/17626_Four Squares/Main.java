import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int n;
	static int ans = 4;
	static int dp[] = new int[50001];

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		// 자연수가 제곱수인 경우
		if ((int) Math.sqrt(n) == Math.sqrt(n)) {
			System.out.println(1);
			return;
		}
		
		Arrays.fill(dp, 5);

		// 제곱수 미리 넣기
		for (int idx = 1; idx * idx <= n; idx++) {
			dp[idx * idx] = 1;
		}

		for (int idx = 2; idx <= n; idx++) {
			if (dp[idx] == 1) {
				continue;
			}
			
			for (int N = 1; N * N < idx; N++) {
				dp[idx] = Math.min(dp[idx], dp[N * N] + dp[idx - N * N]);
			}
		}

		System.out.println(dp[n]);
	}
}
