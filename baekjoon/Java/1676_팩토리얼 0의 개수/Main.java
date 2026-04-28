import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;

	static int ans = 0;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		// 0이 나오려면
		// 1에서 N까지 5로 나누어 떨어지는 수를 찾는다.

		for (int idx = 5; idx <= N; idx++) {

			int num = idx;
			while (num % 5 == 0) {
				ans++;
				num /= 5;
			}
		}
		
		System.out.println(ans);

	}

}
