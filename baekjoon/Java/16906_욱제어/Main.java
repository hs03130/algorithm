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
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static final int ROOT = 1, MAX = 1000, TABLE_LEN = 2;
	static int unused = ROOT + 1;
	static boolean[] check = new boolean[MAX];
	static int[][] next = new int[MAX][TABLE_LEN];

	static int wordCnt, ans;
	static int[][] word;
	static String[] ansWord;
	static final int LEN = 0, IDX = 1;

	static int ctoi(char ch) {
		return ch - '0';
	}

	static void insert(String str) {
		int cur = ROOT;
		for (char c : str.toCharArray()) {
			if (next[cur][ctoi(c)] == -1) next[cur][ctoi(c)] = unused++;
			cur = next[cur][ctoi(c)];
		}
		check[cur] = true;
	}

	static boolean find(String str) {
		int cur = ROOT;
		for (char c : str.toCharArray()) {
			if (next[cur][ctoi(c)] == -1) return false;
			cur = next[cur][ctoi(c)];
		}
		return check[cur];
	}

	static boolean dfs(int len, int idx, String str) {
		if (str.length() == len) {
			insert(str);
			ansWord[idx] = str;
			return true;
		}

		if (!find(str + "0") && dfs(len, idx, str + "0")) return true;
		if (!find(str + "1") && dfs(len, idx, str + "1")) return true;

		return false;
	}

	public static void main(String[] args) throws IOException {
		// 트라이 초기화
		for (int idx = 0; idx < MAX; idx++) {
			Arrays.fill(next[idx], -1);
		}

		// 단어 개수 입력
		wordCnt = Integer.parseInt(br.readLine());
		word = new int[wordCnt][2];

		// 길이 입력 후 오름차순 정렬
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < wordCnt; idx++) {
			word[idx][LEN] = Integer.parseInt(st.nextToken());
			word[idx][IDX] = idx;
		}
		Arrays.sort(word, (o1, o2) -> Integer.compare(o1[LEN], o2[LEN]));

		// 짧은 단어부터 문자열 생성
		ansWord = new String[wordCnt]; 
		ans = 1;
		for (int idx = 0; idx < wordCnt; idx++) {
			if (!dfs(word[idx][LEN], word[idx][IDX], "")) { // 생성 실패
				ans = -1;
				break;
			}
		}

		// 출력
		sb.append(ans + "\n");
		if (ans == 1) {
			// 입력된 순서대로 출력
			for (int idx = 0; idx < wordCnt; idx++) {
				sb.append(ansWord[idx] + "\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}