/*
 * 0,0에서 시작해서 인접한 상하좌우로 이동한다.
 * 한 번 방문한 알파벳과 동일한 알파벳에는 방문할 수 없다.
 * 가장 긴 이동거리를 출력
 * 
 * 1. 입력
 *  1-1 행 길이 | 열 길이
 * 
 * 2. 0,0에서 시작해서 dfs
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int rowSize;
	static int colSize;
	static int[][] board;
	static boolean[] visited;
	static int ans;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static final int ALPHABET_CNT = 26;
	static final int START = 0;

	// 입력
	static void input() throws IOException {
		// 과일 개수 | 뱀 길이 입력
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		board = new int[ALPHABET_CNT][ALPHABET_CNT];
		for (int row = 0; row < rowSize; row++) {
			String rowStr = br.readLine().trim();
			for (int col = 0; col < colSize; col++) {
				// 알파벳은 대문자만 입력된다.
				board[row][col] = rowStr.charAt(col) - 'A';
			}
		}
	}

	static void solve() {
		ans = 1;
		visited = new boolean[ALPHABET_CNT];

		visited[board[START][START]] = true;
		dfs(START, START, 1);
	}

	static void dfs(int x, int y, int len) {
		boolean flag = true;
		// 인접한 칸으로 이동
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx < 0 || nx >= rowSize || ny < 0 || ny >= colSize || visited[board[nx][ny]]) {
				continue;
			}
			flag = false;
			visited[board[nx][ny]] = true;
			dfs(nx, ny, len + 1);
			visited[board[nx][ny]] = false;
		}
		// 더 이상 진행 불가
		if (flag) {
			ans = Math.max(ans, len);
			return;
		}
	}

	public static void main(String args[]) throws IOException {
		input();
		solve();
		System.out.println(ans);
	}

}