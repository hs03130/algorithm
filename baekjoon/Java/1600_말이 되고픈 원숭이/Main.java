import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int jumpCnt;
	static int rowSize;
	static int colSize;
	static int[][] board;

	static Deque<int[]> Q;
	static boolean[][][] visited;
	static int ans;

	static int[] dx = { 0, 1, 0, -1, -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dy = { 1, 0, -1, 0, 1, 2, 2, 1, -1, -2, -2, -1 };
	static final int X = 0;
	static final int Y = 1;
	static final int JUMP = 2;
	static final int DIS = 3;

	// 입력
	static void input() throws Exception {
		// 말처럼 이동 가능한 횟수
		jumpCnt = Integer.parseInt(br.readLine().trim());

		// 보드 크기 입력
		st = new StringTokenizer(br.readLine().trim());
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());

		// 보드 정보 입력
		board = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void solve() throws Exception {
		ans = 0;
		if (rowSize == 1 && colSize == 1) {
			return;
		}
		
		Q = new ArrayDeque<>();
		visited = new boolean[jumpCnt + 1][rowSize][colSize];

		// 0,0 -> W,H로
		Q.offer(new int[] { 0, 0, 0, 0 });
		visited[0][0][0] = true;

		while (!Q.isEmpty()) {
			int[] cur = Q.poll();

			// 일반적인 이동
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];
				if (nx < 0 || nx >= rowSize || ny < 0 || ny >= colSize || board[nx][ny] == 1) {
					continue;
				}
				if (visited[cur[JUMP]][nx][ny]) {
					continue;
				}

				// 도착
				if (nx == rowSize - 1 && ny == colSize - 1) {
					ans = cur[DIS] + 1;
					return;
				}

				visited[cur[JUMP]][nx][ny] = true;
				Q.offer(new int[] { nx, ny, cur[JUMP], cur[DIS] + 1 });
			}
			// 말처럼 움직일 횟수가 남았다면 말처럼 이동
			if (cur[JUMP] < jumpCnt) {
				for (int dir = 4; dir < 12; dir++) {
					int nx = cur[X] + dx[dir];
					int ny = cur[Y] + dy[dir];
					if (nx < 0 || nx >= rowSize || ny < 0 || ny >= colSize || board[nx][ny] == 1) {
						continue;
					}
					if (visited[cur[JUMP] + 1][nx][ny]) {
						continue;
					}

					// 도착
					if (nx == rowSize - 1 && ny == colSize - 1) {
						ans = cur[DIS] + 1;
						return;
					}

					visited[cur[JUMP] + 1][nx][ny] = true;
					Q.offer(new int[] { nx, ny, cur[JUMP] + 1, cur[DIS] + 1 });
				}
			}
		}
		// 도착하지 못함
		ans = -1;
	}

	public static void main(String[] args) throws Exception {
		input();
		solve();
		System.out.println(ans);
	}

}