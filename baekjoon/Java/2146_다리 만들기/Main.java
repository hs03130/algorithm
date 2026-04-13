import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int boardSize, islandNum = 1;
	static int[][] board, dist;
	static boolean[][] visited;
	static ArrayDeque<int[]> Q;
	static int ans = Integer.MAX_VALUE;

	static final int X = 0, Y = 1, OCEAN = 0;
	static final int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		boardSize = Integer.parseInt(br.readLine());
		board = new int[boardSize][boardSize];
		dist = new int[boardSize][boardSize];
		visited = new boolean[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		Q = new ArrayDeque<>();
		// 섬에 번호 부여
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				if (board[row][col] != OCEAN && !visited[row][col]) {
					board[row][col] = islandNum;
					visited[row][col] = true;
					Q.offer(new int[] { row, col });
					while (!Q.isEmpty()) {
						int[] cur = Q.poll();
						for (int dir = 0; dir < 4; dir++) {
							int nx = cur[X] + dx[dir];
							int ny = cur[Y] + dy[dir];

							if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize) continue;
							if (board[nx][ny] == OCEAN || visited[nx][ny]) continue;

							board[nx][ny] = islandNum;
							visited[nx][ny] = true;
							Q.offer(new int[] { nx, ny });
						}
					}
					islandNum++;
				}
			}
		}

		// 섬을 큐에 넣기
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				if (board[row][col] != OCEAN) {
					dist[row][col] = 0;
					Q.offer(new int[] { row, col });
				}
			}
		}

		// 가장 짧은 다리 길이 구하기
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];

				if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize) continue;

				// 같은 섬인 경우
				if (board[cur[X]][cur[Y]] == board[nx][ny]) continue;

				// 다른 섬인 경우 (다른 섬에서 출발하여 이미 도달한 바다도 포함)
				if (board[nx][ny] != OCEAN) {
					ans = Math.min(ans, dist[cur[X]][cur[Y]] + dist[nx][ny]);
				}
				// 바다인 경우
				else {
					board[nx][ny] = board[cur[X]][cur[Y]]; // 출발 섬
					dist[nx][ny] = dist[cur[X]][cur[Y]] + 1;
					Q.offer(new int[] { nx, ny });
				}
			}
		}

		// 출력
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}

/*
 * 다음과 같은 입력이 주어질 때 
 * 1 0 0 0 0 
 * 0 0 0 0 0 
 * 0 0 0 0 0 
 * 0 0 0 0 0 
 * 0 0 0 0 1
 * 
 * dist는 다음과 같다 
 * 0 1 2 3 4 
 * 1 2 3 4 3 
 * 2 3 4 3 2 
 * 3 4 3 2 1 
 * 4 3 2 1 0
 */