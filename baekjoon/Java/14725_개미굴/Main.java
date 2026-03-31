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

	static int unused, foodCnt;
	static boolean[] check;
	static int[][] next;
	static String[] strArr;

	static final int ROOT = 1, MAX = 15 * 15 * 1000, TABLE_LEN = 27;

	static int ctoi(char ch) {
		if(ch == ' ') return 0;
		return ch - 'A' + 1;
	}

	static char itoc(int i) {
		if (i == 0) return ' ';
		return (char) ('A' + i - 1);
	}

	static void insert(String str) {
		int cur = ROOT;
		for (char ch : str.toCharArray()) {
			if (ch == ' ') check[cur] = true;

			if (next[cur][ctoi(ch)] == -1) next[cur][ctoi(ch)] = unused++;

			cur = next[cur][ctoi(ch)];
		}
		check[cur] = true;
	}

	static void dfs(int cur, String str, String depth) {
		// 먹이 끝
		if (check[cur]) sb.append(depth + str + "\n");

		// 아래층 먹이
		if (next[cur][0] != -1) dfs(next[cur][0], "", depth + "--");

		// 현재 먹이 이어서
		for (int idx = 1; idx < TABLE_LEN; idx++) {
			if (next[cur][idx] != -1) dfs(next[cur][idx], str + itoc(idx), depth);
		}
	}

	public static void main(String[] args) throws IOException {
		check = new boolean[MAX];
		next = new int[MAX][TABLE_LEN];
		for (int idx = 0; idx < MAX; idx++) {
			Arrays.fill(next[idx], -1);
		}
		unused = ROOT + 1;

		foodCnt = Integer.parseInt(br.readLine());
		strArr = new String[foodCnt];
		
		// 집합 입력
		for (int idx = 0; idx < foodCnt; idx++) {
			String str = br.readLine();
			if (str.charAt(1) == ' ') strArr[idx] = str.substring(2); // 0~9
			else strArr[idx] = str.substring(3); // 10~15
		}
		Arrays.sort(strArr);
		for (int idx = 0; idx < foodCnt; idx++) {
			insert(strArr[idx]);
		}
		
		// 디렉토리 구조 출력
		dfs(ROOT, "", "");

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}