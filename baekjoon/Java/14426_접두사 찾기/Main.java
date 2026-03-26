import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int unused = 2, setCnt, findCnt;
	static boolean[] check;
	static int[][] next;
	static int ans = 0;

	static final int ROOT = 1, MAX = 500 * 10000;

	static int charToInt(char c) {
		return c - 'a';
	}

	static void insert(String str) {
		int cur = ROOT;
		for (char c : str.toCharArray()) {
			if (next[cur][charToInt(c)] == -1) next[cur][charToInt(c)] = unused++;
			cur = next[cur][charToInt(c)];
		}
		check[cur] = true;
	}

	static boolean find(String str) {
		int cur = ROOT;
		for (char c : str.toCharArray()) {
			if (next[cur][charToInt(c)] == -1) return false;
			cur = next[cur][charToInt(c)];
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		check = new boolean[MAX];
		next = new int[MAX][26];
		for (int idx = 0; idx < MAX; idx++) {
			Arrays.fill(next[idx], -1);
		}

		st = new StringTokenizer(br.readLine());
		setCnt = Integer.parseInt(st.nextToken());
		findCnt = Integer.parseInt(st.nextToken());

		// 집합 입력
		for (int idx = 0; idx < setCnt; idx++) {
			insert(br.readLine());
		}
		
		// 검사할 문자열이 집합에 있는 문자열의 접두사인지 확인
		for (int idx = 0; idx < findCnt; idx++) {
			if (find(br.readLine())) ans++;
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}