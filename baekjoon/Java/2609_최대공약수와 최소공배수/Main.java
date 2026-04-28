import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int num1, num2;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		num1 = Integer.parseInt(st.nextToken());
		num2 = Integer.parseInt(st.nextToken());

		System.out.println(gcd(num1, num2));
		System.out.println(lcm(num1, num2));
	}

	// 최대 공약수
	// 유클리드 호제법 사용
//	static int gcd(int a, int b) {
//		if (b == 0)
//			return a;
//
//		return gcd(b, a % b);
//	}

	// 완전 탐색 방법
	static int gcd(int a, int b) {
		int min = Math.min(a, b);
		for (int i = min; i >= 1; i--) {
			if (a % i == 0 && b % i == 0) {
				return i;
			}
		}
		return 1;
	}

	// 최소 공배수
	static int lcm(int a, int b) {
		// 두 수의 곱 나누기 최대공약수
		return a * b / gcd(a, b);
	}
}
