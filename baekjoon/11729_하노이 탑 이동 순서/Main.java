import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static int cnt;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		sb = new StringBuilder();

		func(1, 3, N);

		System.out.println(cnt);
		System.out.println(sb);
	}

	static void func(int a, int b, int num) {
		if (num == 1) {
			sb.append(a + " " + b + "\n");
			cnt++;
			return;
		}

		func(a, 6 - a - b, num - 1);
		sb.append(a + " " + b + "\n");
		cnt++;
		func(6 - a - b, b, num - 1);
	}
}
