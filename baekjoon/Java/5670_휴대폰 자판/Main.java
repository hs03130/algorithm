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

	static final int ROOT = 1, MAX = 1_000_000, TABLE_LEN = 26, CHILD_CNT = 0;
	static int unused;
	static boolean[] check;
	static int[][] next = new int[MAX][TABLE_LEN + 1];

	static String cmd;
	static int wordCnt;
	static double ans = 0L;

	static int ctoi(char ch) {
		return ch - 'a' + 1;
	}

	static void insert(String str) {
		int cur = ROOT;
		for (char c : str.toCharArray()) {
			if (next[cur][ctoi(c)] == -1) {
				next[cur][ctoi(c)] = unused++;
				next[cur][CHILD_CNT]++;
			}

			cur = next[cur][ctoi(c)];
		}
		check[cur] = true;
	}

	static void dfs(int cur, int cnt) {
		if (check[cur]) {
			ans += cnt;
		}

		for (int idx = 1; idx <= TABLE_LEN; idx++) {
			if (next[cur][idx] == -1) continue;
			
			if (cur == ROOT || check[cur] || next[cur][CHILD_CNT] > 1) dfs(next[cur][idx], cnt + 1);
			else dfs(next[cur][idx], cnt);
		}
	}

	public static void main(String[] args) throws IOException {
		while ((cmd = br.readLine()) != null) {
			// 트라이 초기화
			check = new boolean[MAX];
			for (int idx = 0; idx < MAX; idx++) {
				Arrays.fill(next[idx], -1);
				next[idx][CHILD_CNT] = 0;
			}
			unused = ROOT + 1;
			ans = 0L;

			// 사전 입력
			wordCnt = Integer.parseInt(cmd);
			for (int idx = 0; idx < wordCnt; idx++) {
				insert(br.readLine());
			}

			// 횟수 계산
			dfs(ROOT, 0);

			// 평균 출력
			sb.append(String.format("%.2f", ans / wordCnt) + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}