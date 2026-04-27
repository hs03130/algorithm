import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int maxPassword, passwordCnt;
	static int[] safety;
	static ArrayDeque<Integer> Q;
	static int ans = 0;

	static final int NOT_VISITED = -1, MAX_LEN = 20; // 2^20 = 1_048_576

	public static void main(String[] args) throws IOException {
		maxPassword = Integer.parseInt(br.readLine());
		passwordCnt = Integer.parseInt(br.readLine());

		Q = new ArrayDeque<>();

		safety = new int[maxPassword + 1];
		Arrays.fill(safety, NOT_VISITED);

		// 로그인 시도에 사용된 비밀번호
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < passwordCnt; idx++) {
			int password = Integer.parseInt(st.nextToken());
			safety[password] = 0;
			Q.offer(password);
		}

		while (!Q.isEmpty()) {
			int cur = Q.poll();

			// 안전도가 n인 비밀번호에서 한 자리를 바꾸면 안전도는 n+1이 된다
			// int maxLen = (maxPassword == 0) ? 1 : (int)(Math.log(maxPassword) / Math.log(2)) + 1;
			for (int k = 0; k <= MAX_LEN; k++) { // k <= maxLen 도 가능
				int next = cur ^ (1 << k); // k번째 자리를 바꿈

				if (next > maxPassword || safety[next] != NOT_VISITED) continue;
				safety[next] = safety[cur] + 1;
				Q.offer(next);
			}
		}

		for (int idx = 0; idx <= maxPassword; idx++) {
			ans = Math.max(ans, safety[idx]);
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}