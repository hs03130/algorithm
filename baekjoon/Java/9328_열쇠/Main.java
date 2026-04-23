import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int testCase, boardH, boardW;
	static char[][] board;
	static boolean[][] visited;
	static boolean[] keys;
	static ArrayList<int[]>[] doors;
	static ArrayDeque<int[]> Q;
	static int ans;

	static final char EMPTY = '.', WALL = '*', DOC = '$';
	static final int MAX_KEY_CNT = 26, X = 0, Y = 1;
	static final int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	static boolean isDoor(char ch) { return (ch >= 'A' && ch <= 'Z'); }

	static boolean isKey(char ch) { return (ch >= 'a' && ch <= 'z'); }

	static boolean canMove(int x, int y) {
		if (board[x][y] == WALL || (isDoor(board[x][y]) && !keys[board[x][y] - 'A'])) return false;
		return true;
	}

	static boolean outOfBound(int x, int y) {
		if (x < 0 || x >= boardH || y < 0 || y >= boardW) return true;
		return false;
	}

	static boolean nearVisited(int x, int y) {
		if (x == 0 || x == boardH - 1 || y == 0 || y == boardW - 1) return true;
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (!outOfBound(nx, ny) && visited[nx][ny]) return true;
		}
		return false;
	}

	static void openDoors(int doorIdx) {
		for (int[] cur : doors[doorIdx]) board[cur[X]][cur[Y]] = EMPTY;
		doors[doorIdx].clear();
	}

	static void entrance(int x, int y) {
		// 문서 획득
		if (board[x][y] == DOC) ans++;

		// 새로운 열쇠 획득
		if (isKey(board[x][y]) && !keys[board[x][y] - 'a']) {
			keys[board[x][y] - 'a'] = true;
			openDoors(board[x][y] - 'a');
		}

		board[x][y] = EMPTY;
		visited[x][y] = true;
		Q.offer(new int[] { x, y });
	}

	public static void main(String[] args) throws IOException {
		testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			// 초기화
			ans = 0;
			keys = new boolean[MAX_KEY_CNT];
			doors = new ArrayList[MAX_KEY_CNT];
			for (int idx = 0; idx < MAX_KEY_CNT; idx++) {
				doors[idx] = new ArrayList<>();
			}
			Q = new ArrayDeque<>();

			// 보드 입력
			st = new StringTokenizer(br.readLine());
			boardH = Integer.parseInt(st.nextToken());
			boardW = Integer.parseInt(st.nextToken());

			board = new char[boardH][];
			visited = new boolean[boardH][boardW];
			for (int row = 0; row < boardH; row++) {
				board[row] = br.readLine().toCharArray();
				// 문 위치 기록
				for (int col = 0; col < boardW; col++) {
					if (isDoor(board[row][col])) doors[board[row][col] - 'A'].add(new int[] { row, col });
				}
			}

			// 시작시 가지고 있는 열쇠
			String initKeys = br.readLine();
			if (initKeys.charAt(0) != '0') {
				for (char key : initKeys.toCharArray()) {
					keys[key - 'a'] = true;
					openDoors(key - 'a');
				}
			}

			// 출입구 찾기
			// 남북
			for (int col = 0; col < boardW; col++) {
				if (canMove(0, col)) entrance(0, col);
				if (canMove(boardH - 1, col)) entrance(boardH - 1, col);
			}
			// 동서
			for (int row = 1; row < boardH - 1; row++) {
				if (canMove(row, 0)) entrance(row, 0);
				if (canMove(row, boardW - 1)) entrance(row, boardW - 1);
			}

			// 문서 찾기
			while (!Q.isEmpty()) {
				ArrayDeque<int[]> tmpQ = new ArrayDeque<>();

				while (!Q.isEmpty()) {
					int[] cur = Q.poll();

					// 인접한 곳으로 이동
					for (int dir = 0; dir < 4; dir++) {
						int nx = cur[X] + dx[dir];
						int ny = cur[Y] + dy[dir];

						if (outOfBound(nx, ny) || visited[nx][ny] || !canMove(nx, ny)) continue;

						// 문서 획득
						if (board[nx][ny] == DOC) ans++;
						
						// 새로운 열쇠 획득
						if (isKey(board[nx][ny]) && !keys[board[nx][ny] - 'a']) {
							keys[board[nx][ny] - 'a'] = true;
							for (int[] door : doors[board[nx][ny] - 'a']) {
								// 방문한 곳과 인접한 문 -> 방문 가능
								if (nearVisited(door[X], door[Y])) {
									visited[door[X]][door[Y]] = true;
									tmpQ.offer(door);
								}
								board[door[X]][door[Y]] = EMPTY;
							}
							doors[board[nx][ny] - 'a'].clear();
						}
						
						board[nx][ny] = EMPTY;
						visited[nx][ny] = true;
						tmpQ.offer(new int[] { nx, ny });
					}
				}

				Q = tmpQ;
			}

			// 출력
			sb.append(ans + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}