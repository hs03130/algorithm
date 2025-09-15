import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int testCase, boardSize;
	static int startX, startY, targetX, targetY;
	static int[][] board;
	static Deque<int[]> Q;

	static int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static final int X = 0, Y = 1;

	public static void main(String[] args) throws IOException {

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < testCase; tc++) {
			boardSize = Integer.parseInt(br.readLine());
			board = new int[boardSize][boardSize];

			st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			targetX = Integer.parseInt(st.nextToken());
			targetY = Integer.parseInt(st.nextToken());

			Q = new ArrayDeque<>();
			Q.offer(new int[] { startX, startY });
			while (!Q.isEmpty()) {
				int[] cur = Q.pop();

				if (cur[X] == targetX && cur[Y] == targetY) {
					break;
				}

				for (int dir = 0; dir < 8; dir++) {
					int nx = cur[X] + dx[dir];
					int ny = cur[Y] + dy[dir];

					if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize || board[nx][ny] > 0) {
						continue;
					}

					board[nx][ny] = board[cur[X]][cur[Y]] + 1;
					Q.offer(new int[] { nx, ny });
				}
			}

			System.out.println(board[targetX][targetY]);
		}

	}

}
