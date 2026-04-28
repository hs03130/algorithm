import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N, len;
	static char[] line;
	static int ans = 0;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());
		len = Integer.parseInt(br.readLine());
		line = br.readLine().toCharArray();

		int num = 0;
		for (int idx = 1; idx < len - 1; idx++) {
			if (line[idx - 1] == 'I' && line[idx] == 'O' && line[idx + 1] == 'I') {
				num++;
				if (num == N) {
					num--;
					ans++;
				}
				idx++;
			} else {
				num = 0;
			}
		}

		System.out.println(ans);
	}

}
