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

	static int unused, nameCnt;
	static int[] check;
	static int[][] next;

	static final int ROOT = 1, MAX = 10 * 100000, TABLE_LEN = 26;

	static int ctoi(char ch) {
		return ch - 'a';
	}

	static void insert(String str) {
		int cur = ROOT;

		boolean done = false;
		String ans = "";
		for (char ch : str.toCharArray()) {
			ans += ch;
			if (next[cur][ctoi(ch)] == -1) {
				next[cur][ctoi(ch)] = unused++;

				if (!done) {
					sb.append(ans + "\n");
					done = true;
				}
			}
			cur = next[cur][ctoi(ch)];
		}

		check[cur]++;

		// 가능한 별칭이 없는 경우
		if (!done) {
			if (check[cur] > 1) ans += String.valueOf(check[cur]);
			sb.append(ans + "\n");
		}
	}

	public static void main(String[] args) throws IOException {
		check = new int[MAX];
		next = new int[MAX][TABLE_LEN];
		for (int idx = 0; idx < MAX; idx++) {
			Arrays.fill(next[idx], -1);
		}
		unused = ROOT + 1;

		nameCnt = Integer.parseInt(br.readLine());

		// 집합 입력
		for (int idx = 0; idx < nameCnt; idx++) {
			insert(br.readLine());
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}