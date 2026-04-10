import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int boardH, boardW, cnt = 1;
	static int[][] board;
	static boolean[][] visited;
	static ArrayDeque<int[]> Q;
	static int ans;

	static final int X = 0, Y = 1;
	static final int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());
		board = new int[boardH][boardW];
		visited = new boolean[boardH][boardW];
		Q = new ArrayDeque<>();

		// 빙산 높이 입력
		for (int row = 0; row < boardH; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < boardW; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		// 두 덩어리 이상으로 분리될때까지 진행
		for (ans = -1; cnt == 1; ans++) {
			
			// 방문 기록 초기화
			for (int idx = 0; idx < boardH; idx++) {
				Arrays.fill(visited[idx], false);
			}

			cnt = 0;
			for (int row = 0; row < boardH; row++) {
				for (int col = 0; col < boardW; col++) {
					if (board[row][col] > 0 && !visited[row][col]) {
						cnt++;

						// BFS
						visited[row][col] = true;
						Q.offer(new int[] { row, col });
						while (!Q.isEmpty()) {
							int[] cur = Q.poll();
							for (int dir = 0; dir < 4; dir++) {
								int nx = cur[X] + dx[dir];
								int ny = cur[Y] + dy[dir];

								if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW) continue;

								// 높이 감소
								if (!visited[nx][ny] && board[nx][ny] == 0) {
									if (board[cur[X]][cur[Y]] > 0) board[cur[X]][cur[Y]]--;
									continue;
								}

								// 같은 덩어리 이어서 진행
								if (!visited[nx][ny]) {
									visited[nx][ny] = true;
									Q.offer(new int[] { nx, ny });
								}
							}
						}
					}
				}
			}
		}

		if (cnt < 2) ans = 0;

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}