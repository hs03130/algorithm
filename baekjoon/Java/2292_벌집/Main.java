import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;

	static int ans = 1;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		// 1 6 12 18 24 ... 증가

		// 시작칸(1) 포함해서 지나가는 방 개수 세기
		int sum = 1;
		while (sum < N) {
			sum += 6 * ans++;
		}

		System.out.println(ans);

	}

}
