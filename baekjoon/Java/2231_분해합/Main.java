import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int ans;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		ans = 0;

		// N이 몇자리인지 확인 (확인할 범위 줄이기)
		int tempN = N;
		int len = 0;
		while (tempN > 0) {
			tempN /= 10;
			len++;
		}
		
		for (int idx = N - (len * 9); idx < N; idx++) {
			int num = idx;
			int sum = idx;
			while (num > 0) {
				sum += num % 10;
				num /= 10;
			}
			if (sum == N) {
				ans = idx;
				break;
			}
		}

		System.out.println(ans);
	}

}
