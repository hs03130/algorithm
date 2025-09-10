
/**
 * 1~1000 자리 수의 오르막 수를 구한다.
 * 숫자는 0으로 시작할 수 있다. 예를들어 N이 3일 때, 011도 오르막 수이다.
 * 오르막 수의 개수를 10007로 나눈 나머지를 출력 
 */

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
	static int[][] dp;
	static int ans = 0;

	static final int MOD = 10007;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][10];
		for (int num = 0; num <= 9; num++) {
			dp[1][num] = 1; // 0~9
		}

		for (int len = 2; len <= N; len++) { // 수의 길이
			for (int num = 0; num <= 9; num++) { // 끝나는 숫자
				for (int k = 0; k <= num; k++) { // num보다 작거나 같은 숫자로 끝난 수 개수를 더한다
					dp[len][num] = (dp[len][num] + dp[len - 1][k]) % MOD;
				}
			}
		}

		for (int num = 0; num <= 9; num++) {
			ans = (ans + dp[N][num]) % MOD;
		}
		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}