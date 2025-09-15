import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int boardSize;
	static int[][] board;

	static Deque<int[]> Q;
	static int[][] visited;
	static int maxHeight = 0;
	static int ans = 0;

	static int[] dx = new int[] { 1, 0, -1, 0 };
	static int[] dy = new int[] { 0, 1, 0, -1 };
	static final int X = 0, Y = 1;

	public static void main(String[] args) throws IOException {

		boardSize = Integer.parseInt(br.readLine());
		board = new int[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(board[row][col], maxHeight);
			}
		}

		Q = new ArrayDeque<>();
		for (int height = 0; height <= maxHeight; height++) {
			visited = new int[boardSize][boardSize];

			int cnt = 0;
			for (int row = 0; row < boardSize; row++) {
				for (int col = 0; col < boardSize; col++) {
					if (board[row][col] > height && visited[row][col] == 0) {
						visited[row][col] = 1;
						Q.offer(new int[] { row, col });
						while (!Q.isEmpty()) {
							int[] cur = Q.pop();
							for (int dir = 0; dir < 4; dir++) {
								int nx = cur[X] + dx[dir];
								int ny = cur[Y] + dy[dir];

								if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize) {
									continue;
								}

								if (board[nx][ny] <= height || visited[nx][ny] != 0) {
									continue;
								}

								visited[nx][ny] = 1;
								Q.offer(new int[] { nx, ny });
							}
						}
						cnt++;
					}
				}
			}

			if (cnt > ans)
				ans = cnt;
		}

		System.out.println(ans);
	}

}
