import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int testCase, cnt;
	static int[] wish, state;
	static int ans;

	static final int NOT_VISITED = 0, MAKE_TEAM = -1;

	static void check(int start) {
		int cur = start;
		while (state[cur] == NOT_VISITED || state[cur] == start) {
			// 팀 구성 가능
			if (state[cur] == start) {
				while (state[cur] == start) {
					state[cur] = MAKE_TEAM;
					cur = wish[cur];
				}
				return;
			}

			state[cur] = start;
			cur = wish[cur];
		}
	}

	public static void main(String[] args) throws IOException {
		testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			cnt = Integer.parseInt(br.readLine());
			wish = new int[cnt + 1];
			state = new int[cnt + 1];

			st = new StringTokenizer(br.readLine());
			for (int idx = 1; idx <= cnt; idx++) {
				wish[idx] = Integer.parseInt(st.nextToken());
			}

			// 팀 매칭이 됐는지 확인
			for (int idx = 1; idx <= cnt; idx++) {
				if (state[idx] == NOT_VISITED) check(idx);
			}

			// 팀을 구성하지 못한 사람 세기
			ans = 0;
			for (int idx = 1; idx <= cnt; idx++) {
				if (state[idx] != MAKE_TEAM) ans++;
			}

			// 출력
			sb.append(ans + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}