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

	static final int ROOT = 1, MAX = 8 * 300000, TABLE_LEN = 26, DICE_SIZE = 4;
	static final int[] SCORE_TABLE = { 0, 0, 0, 1, 1, 2, 3, 5, 11 };
	static final int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 }, dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static int unused = ROOT + 1, wordCnt, boardCnt, score, findWordCnt, curBoard = 2;
	static String maxLenWord;
	static int[] check = new int[MAX];
	static int[][] next = new int[MAX][TABLE_LEN];
	static char[][] board = new char[DICE_SIZE][];
	static boolean[][] visited = new boolean[DICE_SIZE][DICE_SIZE];

	static int ctoi(char ch) {
		return ch - 'A';
	}

	static void insert(String str) {
		int cur = ROOT;
		for (char ch : str.toCharArray()) {
			if (next[cur][ctoi(ch)] == -1) next[cur][ctoi(ch)] = unused++;
			cur = next[cur][ctoi(ch)];
		}
		check[cur]++;
	}

	static void find(int row, int col, int cur, String str) {
		// 사전에 없는 단어
		if (cur == -1) return;

		// 단어의 끝이고, 이번 보드에서 아직 방문하지 않음(처음 찾은 단어)
		if (check[cur] != 0 && check[cur] < curBoard) {
			check[cur] = curBoard;
			score += SCORE_TABLE[str.length()];
			findWordCnt++;
			if (compare(maxLenWord, str)) maxLenWord = str;
		}

		// 이어서 보드 탐색
		for (int dir = 0; dir < 8; dir++) {
			int nx = row + dx[dir];
			int ny = col + dy[dir];
			if (nx < 0 || nx >= DICE_SIZE || ny < 0 || ny >= DICE_SIZE || visited[nx][ny]) continue;

			visited[nx][ny] = true;
			find(nx, ny, next[cur][ctoi(board[nx][ny])], str + board[nx][ny]);
			visited[nx][ny] = false;
		}
	}

	// o2가 o1보다 긴 단어인지 반환. 길이가 같은 경우 o2가 사전순으로 앞서는지 반환
	static boolean compare(String o1, String o2) {
		if (o1.length() == o2.length()) return o1.compareTo(o2) > 0;
		return o1.length() < o2.length();
	}

	public static void main(String[] args) throws IOException {
		// 트라이 초기화
		for (int idx = 0; idx < MAX; idx++) {
			Arrays.fill(next[idx], -1);
		}

		// 단어 사전 입력
		wordCnt = Integer.parseInt(br.readLine());
		for (int idx = 0; idx < wordCnt; idx++) {
			insert(br.readLine());
		}
		br.readLine(); // 빈줄

		// 각 Boggle에 대해 점수, 가장 긴 단어, 찾은 단어 개수를 출력
		boardCnt = Integer.parseInt(br.readLine());
		for (int bIdx = 0; bIdx < boardCnt; bIdx++) {
			// 보드 입력
			for (int row = 0; row < DICE_SIZE; row++) {
				board[row] = br.readLine().toCharArray();
			}

			// 게임 진행
			score = 0; findWordCnt = 0; maxLenWord = "";
			for (int row = 0; row < DICE_SIZE; row++) {
				for (int col = 0; col < DICE_SIZE; col++) {
					visited[row][col] = true;
					find(row, col, next[ROOT][ctoi(board[row][col])], board[row][col] + "");
					visited[row][col] = false;
				}
			}

			// 출력
			sb.append(score + " " + maxLenWord + " " + findWordCnt + "\n");

			// 다음판 진행
			curBoard++; // 현재 진행중인 보드 번호 증가(2부터 시작)
			if (bIdx < boardCnt - 1) br.readLine(); // 빈줄
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}