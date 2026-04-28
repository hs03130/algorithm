import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static int cnt;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		for (int idx = 666;; idx++) {
			if (String.valueOf(idx).contains("666")) {
				cnt++;
			}
			if (cnt == N) {
				System.out.println(idx);
				return;
			}
		}

	}

}
