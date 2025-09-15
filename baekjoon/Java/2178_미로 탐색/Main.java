import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int rowSize;
	static int colSize;
	static int[][] board;
	static boolean[][] visited;
	static int[][] distance;
	static Deque<int[]> Q;
	static int ans;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[] START = { 0, 0 };
	static int[] END;
	static int X = 0;
	static int Y = 1;

	// ют╥б
	static void input() throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		board = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			String str = br.readLine().trim();
			for (int col = 0; col < colSize; col++) {
				board[row][col] = str.charAt(col) - '0';
			}
		}
		END = new int[] { rowSize - 1, colSize - 1 };
	}

	static void solve() {
		visited = new boolean[rowSize][colSize];
		distance = new int[rowSize][colSize];
		Q = new ArrayDeque<>();

		visited[START[X]][START[Y]] = true;
		distance[START[X]][START[Y]] = 1;
		Q.offer(new int[] { START[X], START[Y] });
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];
				if (nx < 0 || nx >= rowSize || ny < 0 || ny >= colSize) {
					continue;
				}
				if (visited[nx][ny] || board[nx][ny] == 0) {
					continue;
				}
				visited[nx][ny] = true;
				distance[nx][ny] = distance[cur[X]][cur[Y]] + 1;
				Q.offer(new int[] { nx, ny });
			}
		}

		ans = distance[END[X]][END[Y]];
	}

	public static void main(String[] args) throws Exception {
		input();
		solve();
		System.out.println(ans);
	}

}