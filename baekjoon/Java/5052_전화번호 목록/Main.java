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

	static int testCase, unused, numCnt;
	static boolean[] check;
	static int[][] next;

	static final int ROOT = 1, MAX = 10 * 10000;

	static int charToInt(char c) {
		return c - '0';
	}

	static boolean insert(String str) {
		int cur = ROOT;
		for (char c : str.toCharArray()) {
			if (next[cur][charToInt(c)] == -1) next[cur][charToInt(c)] = unused++;
			cur = next[cur][charToInt(c)];

			// 다른 전화번호가 접두사로 사용됨
			if (check[cur]) return false;
		}
		
		// 현재 전화번호가 다른 전화번호의 접두사로 사용됨
		if(cur != unused - 1) return false;
		
		return check[cur] = true;
	}

	public static void main(String[] args) throws IOException {
		testCase = Integer.parseInt(br.readLine());

		while (testCase-- > 0) {
			check = new boolean[MAX];
			next = new int[MAX][10];
			for (int idx = 0; idx < MAX; idx++) {
				Arrays.fill(next[idx], -1);
			}
			unused = ROOT + 1;

			numCnt = Integer.parseInt(br.readLine());

			// 집합 입력
			boolean ans = true;
			for (int idx = 0; idx < numCnt; idx++) {
				if(!insert(br.readLine())) ans = false;
			}

			if (ans) sb.append("YES\n");
			else sb.append("NO\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}