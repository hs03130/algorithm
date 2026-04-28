import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int boardH, boardW;
	static int[][] board;
	static int ans = 0;

	static int[][] visited;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static int[][] d = { { 0, 1, 0, 1, 0, -1 }, { 0, 0, -1, 1, -1, 0 }, { 0, 1, -1, 1, 0, 0 }, { 1, 0, -1, 0, -1, 0 } };

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());

		board = new int[boardH][boardW];
		visited = new int[boardH][boardW];
		for (int row = 0; row < boardH; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < boardW; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		for (int row = 0; row < boardH; row++) {
			for (int col = 0; col < boardW; col++) {
				visited[row][col] = 1;
				dfs(row, col, 1, board[row][col]);
				visited[row][col] = 0;

				// ぬ で た っ
				for (int type = 0; type < 4; type++) {
					int sum = board[row][col];
					for (int dir = 0; dir < 3; dir++) {
						if (row + d[type][dir] < 0 || row + d[type][dir] >= boardH || col + d[type][3 + dir] < 0
								|| col + d[type][3 + dir] >= boardW)
							break;
						sum += board[row + d[type][dir]][col + d[type][3 + dir]];
					}
					if (sum > ans) {
						ans = sum;
					}
				}

			}
		}

		System.out.println(ans);
	}

	static void dfs(int x, int y, int len, int sum) {
		if (len == 4) {
			if (sum > ans) {
				ans = sum;
			}
			return;
		}

		for (int dir = 0; dir < 4; dir++) {
			int nextX = x + dx[dir];
			int nextY = y + dy[dir];

			if (nextX < 0 || nextX >= boardH || nextY < 0 || nextY >= boardW)
				continue;
			if (visited[nextX][nextY] == 1)
				continue;

			visited[nextX][nextY] = 1;
			dfs(nextX, nextY, len + 1, sum + board[nextX][nextY]);
			visited[nextX][nextY] = 0;
		}

	}

}
