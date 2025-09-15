
/**
 * 1~1000 �ڸ� ���� ������ ���� ���Ѵ�.
 * ���ڴ� 0���� ������ �� �ִ�. ������� N�� 3�� ��, 011�� ������ ���̴�.
 * ������ ���� ������ 10007�� ���� �������� ��� 
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

		for (int len = 2; len <= N; len++) { // ���� ����
			for (int num = 0; num <= 9; num++) { // ������ ����
				for (int k = 0; k <= num; k++) { // num���� �۰ų� ���� ���ڷ� ���� �� ������ ���Ѵ�
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