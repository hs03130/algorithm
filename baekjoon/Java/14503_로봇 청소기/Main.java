import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int boardH, boardW, x, y, dir;
	static int[][] board;
	static int ans = 0;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static final int X = 0, Y = 1;
	static final int DIRTY = 0, WALL = 1, CLEAN = 2;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());

		board = new int[boardH][boardW];
		for (int row = 0; row < boardH; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < boardW; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			// 현재 칸이 청소되지 않은 경우 현재 칸을 청소한다
			if (board[x][y] == DIRTY) {
				board[x][y] = CLEAN;
				ans++;
			}

			// 주변 4칸 중 청소되지 않은 칸이 존재
			boolean move = false;
			int tmpDir = dir;
			for (int idx = 0; idx < 4; idx++) {
				// 반시계방향으로 90도 회전
				tmpDir--;
				if (tmpDir == -1)
					tmpDir = 3;

				// 앞칸 범위 확인
				int nx = x + dx[tmpDir];
				int ny = y + dy[tmpDir];
				if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW) {
					continue;
				}

				// 앞칸이 청소되어 있지 않으면 이동
				if (board[nx][ny] == DIRTY) {
					x = nx;
					y = ny;
					dir = tmpDir;
					move = true;
					break;
				}
			}

			// 주변 4칸은 모두 청소되어 있음
			if (!move) {
				// 뒤칸 범위 확인
				int nx = x - dx[dir];
				int ny = y - dy[dir];
				// 벽이라 후진할 수 없으면 작동을 멈춘다
				if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW || board[nx][ny] == WALL) {
					break;
				}

				// 후진한다
				x = nx;
				y = ny;
			}
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
