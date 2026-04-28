import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int n, r;

	static int[][] dp;

	static int ans;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		dp = new int[n + 1][r + 1];
		
		ans = nCr(n, r);
		System.out.println(ans);
	}

	static int nCr(int N, int R) {
		if (R == 0 || R == N)
			return 1;
		
		if (dp[N][R] != 0) return dp[N][R];

		return dp[N][R] = nCr(N - 1, R - 1) + nCr(N - 1, R);
	}

}
