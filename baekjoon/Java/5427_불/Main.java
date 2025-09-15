import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int testCase;
	static int boardW, boardH, startX, startY;
	static char[][] board;
	static int[][] fire, person;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static final int X = 0, Y = 1;

	public static void main(String[] args) throws IOException {

		testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {

			st = new StringTokenizer(br.readLine());
			boardW = Integer.parseInt(st.nextToken());
			boardH = Integer.parseInt(st.nextToken());

			board = new char[boardH][];
			fire = new int[boardH][boardW];
			person = new int[boardH][boardW];



			Deque<int[]> Q = new ArrayDeque<>();
			for (int row = 0; row < boardH; row++) {
				board[row] = br.readLine().toCharArray();
				for (int col = 0; col < boardW; col++) {
					if (board[row][col] == '#') {
						fire[row][col] = -1;
						person[row][col] = -1;
					} else if (board[row][col] == '*') {
						Q.offer(new int[] { row, col });
						fire[row][col] = 1;
					} else if (board[row][col] == '@') {
						startX = row;
						startY = col;
						person[row][col] = 1;
					}
				}
			}

			// 불
			while (!Q.isEmpty()) {
				int[] cur = Q.pop();
				for (int dir = 0; dir < 4; dir++) {
					int nx = cur[X] + dx[dir];
					int ny = cur[Y] + dy[dir];

					if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW || fire[nx][ny] != 0) {
						continue;
					}
					fire[nx][ny] = fire[cur[X]][cur[Y]] + 1;
					Q.offer(new int[] { nx, ny });
				}
			}

			Q = new ArrayDeque<>();
			Q.offer(new int[] { startX, startY });

			int ans = -1;
			while (!Q.isEmpty() && ans == -1) {
				int[] cur = Q.pop();
				for (int dir = 0; dir < 4; dir++) {
					int nx = cur[X] + dx[dir];
					int ny = cur[Y] + dy[dir];

					// 건물 밖 탈출 성공
					if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW) {
						ans = person[cur[X]][cur[Y]];
						break;
					}

					// 이미 지나갔거나, 벽인 경우
					if (person[nx][ny] != 0) {
						continue;
					}
					
					// 불이 먼저 혹은 동시에 붙은 경우
					if (fire[nx][ny] != 0 && fire[nx][ny] <= person[cur[X]][cur[Y]] + 1) {
						continue;
					}

					person[nx][ny] = person[cur[X]][cur[Y]] + 1;
					Q.offer(new int[] { nx, ny });
				}
			}

			if (ans == -1) {
				System.out.println("IMPOSSIBLE");
			} else {
				System.out.println(ans);
			}
		}
	}

}
