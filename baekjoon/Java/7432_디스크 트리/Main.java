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

	static int unused, dirCnt;
	static boolean[] check, visited;
	static int[][] next;
	static String[] strArr;

	static final int ROOT = 1, MAX = 80 * 500, TABLE_LEN = 53;
	static final String TABLE = "\\!#$%&'()-0123456789@ABCDEFGHIJKLMNOPQRSTUVWXYZ^_`{}~";

	static int ctoi(char ch) {
		return TABLE.indexOf(ch);
	}

	static char itoc(int i) {
		return TABLE.charAt(i);
	}

	static void insert(String str) {
		int cur = ROOT;
		for (char ch : str.toCharArray()) {
			if (ch == '\\')
				check[cur] = true;

			int cIdx = ctoi(ch);
			if (next[cur][cIdx] == -1) next[cur][cIdx] = unused++;

			cur = next[cur][cIdx];
		}
		check[cur] = true;
	}

	static void dfs(int cur, String str, String depth) {
		// 디렉토리 끝
		if (check[cur]) {
			sb.append(depth + str + "\n");
		}

		// 하위 디렉토리
		if (next[cur][0] != -1) {
			dfs(next[cur][0], "", depth + " ");
		}

		// 현재 디렉토리 이어서
		for (int idx = 1; idx < TABLE_LEN; idx++) {
			if (next[cur][idx] != -1) dfs(next[cur][idx], str + itoc(idx), depth);
		}
	}

	public static void main(String[] args) throws IOException {
		check = new boolean[MAX];
		visited = new boolean[MAX];
		next = new int[MAX][TABLE_LEN];
		for (int idx = 0; idx < MAX; idx++) {
			Arrays.fill(next[idx], -1);
		}
		unused = ROOT + 1;

		dirCnt = Integer.parseInt(br.readLine());
		strArr = new String[dirCnt];

		// 집합 입력
		for (int idx = 0; idx < dirCnt; idx++) {
			strArr[idx] = br.readLine();
		}
		Arrays.sort(strArr);
		for (int idx = 0; idx < dirCnt; idx++) {
			insert(strArr[idx]);
		}

		// 디렉토리 구조 출력
		dfs(ROOT, "", "");

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}