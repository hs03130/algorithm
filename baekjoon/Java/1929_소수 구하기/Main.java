import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int left, right;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());

		left = Integer.parseInt(st.nextToken());
		right = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		for (int num = left; num <= right; num++) {
			// 소수인지 판별
			if (num == 2) {
				sb.append("2\n");
			} else if (num > 1 && num % 2 != 0) {
				int flag = 1;
				for (int p = 3; p * p <= num; p += 2) {
					if (num % p == 0) {
						flag = 0;
						break;
					}
				}
				if (flag == 1) {
					sb.append(num + "\n");
				}
			}
		}
		System.out.println(sb);
	}

}
