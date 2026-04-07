import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static final int ROOT = 1, MAX = 20 * 1000, TABLE_LEN = 63;
	static int unused;
	static boolean[] check, undeletable;
	static int[][] next = new int[MAX][TABLE_LEN];

	static int testCase, deleteCnt, saveCnt, ans = 0;

	static int ctoi(char ch) {
		if (ch - 'A' >= 0 && ch - 'A' < 26) return ch - 'A'; // 0~25
		if (ch - 'a' >= 0 && ch - 'a' < 26) return ch - 'a' + 26; // 26~51
		if (ch - '0' >= 0 && ch - '0' < 10) return ch - '0' + 52; // 52~61
		return 62; // .
	}

	static void insert(String str) {
		int cur = ROOT;
		for (char c : str.toCharArray()) {
			if (next[cur][ctoi(c)] == -1) next[cur][ctoi(c)] = unused++;
			cur = next[cur][ctoi(c)];
		}
		check[cur] = true;
	}

	static void save(String str) {
		int cur = ROOT;
		for (char c : str.toCharArray()) {
			if (next[cur][ctoi(c)] == -1) return;
			undeletable[next[cur][ctoi(c)]] = true;
			cur = next[cur][ctoi(c)];
		}
	}

	static void dfs(int cur) {
		if (check[cur]) ans++;

		for (int idx = 0; idx < TABLE_LEN; idx++) {
			if (next[cur][idx] == -1) continue;

			if (undeletable[next[cur][idx]]) {
				dfs(next[cur][idx]);
			} else {
				ans++;
				continue;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			// 트라이 초기화
			check = new boolean[MAX];
			undeletable = new boolean[MAX];
			for (int idx = 0; idx < MAX; idx++) {
				Arrays.fill(next[idx], -1);
			}
			unused = ROOT + 1;
			ans = 0;

			// 삭제할 단어 입력
			deleteCnt = Integer.parseInt(br.readLine());
			for (int idx = 0; idx < deleteCnt; idx++) {
				insert(br.readLine());
			}

			// 저장할 단어 입력
			saveCnt = Integer.parseInt(br.readLine());
			if (saveCnt ==0) {
				sb.append("1\n");
				continue;
			}
			for (int idx = 0; idx < saveCnt; idx++) {
				save(br.readLine());
			}

			// 단어 삭제
			dfs(ROOT);

			// 출력
			sb.append(ans + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}