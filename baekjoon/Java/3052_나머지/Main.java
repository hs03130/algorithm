import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int cnt[] = new int[42];
	static int ans = 0;

	public static void main(String[] args) throws IOException {

		for (int index = 0; index < 10; index++) {
			int num = Integer.parseInt(br.readLine());
			cnt[num % 42] = 1;
		}

		for (int index = 0; index < 42; index++) {
			ans += cnt[index];
		}

		System.out.println(ans);
	}

}
