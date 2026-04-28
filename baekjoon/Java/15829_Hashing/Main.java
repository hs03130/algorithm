import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static final int R = 31;
	static final int M = 1234567891;

	static int len;
	static String L; // 영문 소문자로만 이루어진 문자열

	static long ans;

	public static void main(String[] args) throws IOException {

		len = Integer.parseInt(br.readLine());
		L = br.readLine();

		long sum = 0;
		for (int idx = 0; idx < len; idx++) {
			int charValue = L.charAt(idx) - 'a' + 1;

			// R은 31, idx는 최대 49로 31^49는 너무 큰 숫자므로 재귀로 계산
			long power = modPow(R, idx, M);

			// sum = a(idx) * r^idx 의 합

			// ans = sum % M
			// = (a(1)r^1 + a(2)r^2 ... ) % M
			// = (a(1)r^1 % M + a(2)r^2 % M ... ) % M
			sum = (sum + (charValue * power) % M) % M;

		}

		ans = sum; // sum % M

		System.out.println(ans);

	}

	// base^exp 재귀로 나눠서 계산하기
	// % mod 로 범위 넘지 않게 하기

	// 31^48 % mod = (31^24 % mod * 31^24 % mod) % mod
	static long modPow(long base, long exp, long mod) {
		if (exp == 0)
			return 1; // base^0 = 1
		if (exp == 1)
			return base % mod;

		// base^exp = base^(exp/2) * base^(exp/2)
		long half = modPow(base, exp / 2, mod);
		long result = (half * half) % mod;

		if (exp % 2 == 1) {
			result = (result * base) % mod;
		}

		return result;
	}

}
