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
	static StringTokenizer st;

	static int boardH, boardW, breakCnt;
	static char[][] board;
	static int[][][] dist;
	static ArrayDeque<int[]> Q;
	static int ans = Integer.MAX_VALUE;

	static final char WALL = '1';
	static final int X = 0, Y = 1, BROKEN = 2, NOT_VISITED = 0, DAY = 1;
	static final int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());
		breakCnt = Integer.parseInt(st.nextToken());

		board = new char[boardH][boardW];
		dist = new int[boardH][boardW][breakCnt + 1];

		// 맵 입력
		for (int row = 0; row < boardH; row++) {
			board[row] = br.readLine().toCharArray();
		}

		Q = new ArrayDeque<>();

		// 출발점
		dist[0][0][0] = 1;
		Q.offer(new int[] { 0, 0, 0 });

		// 이동
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];

				// 맵 범위 벗어남
				if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW) continue;

				// 벽이고 부술 수 있음
				if (board[nx][ny] == WALL && cur[BROKEN] < breakCnt) {
					// 낮
					if (dist[cur[X]][cur[Y]][cur[BROKEN]] % 2 == DAY) {
						if (dist[nx][ny][cur[BROKEN] + 1] != NOT_VISITED && dist[nx][ny][cur[BROKEN] + 1] <= dist[cur[X]][cur[Y]][cur[BROKEN]] + 1) continue;
						dist[nx][ny][cur[BROKEN] + 1] = dist[cur[X]][cur[Y]][cur[BROKEN]] + 1;
						Q.offer(new int[] { nx, ny, cur[BROKEN] + 1 });
					}
					// 밤
					else {
						if (dist[nx][ny][cur[BROKEN] + 1] != NOT_VISITED && dist[nx][ny][cur[BROKEN] + 1] <= dist[cur[X]][cur[Y]][cur[BROKEN]] + 2) continue;
						dist[nx][ny][cur[BROKEN] + 1] = dist[cur[X]][cur[Y]][cur[BROKEN]] + 2;
						Q.offer(new int[] { nx, ny, cur[BROKEN] + 1 });
					}
				}
				// 벽이 아님
				else if (board[nx][ny] != WALL) {
					if (dist[nx][ny][cur[BROKEN]] != NOT_VISITED && dist[nx][ny][cur[BROKEN]] <= dist[cur[X]][cur[Y]][cur[BROKEN]] + 1) continue;
					dist[nx][ny][cur[BROKEN]] = dist[cur[X]][cur[Y]][cur[BROKEN]] + 1;
					Q.offer(new int[] { nx, ny, cur[BROKEN] });
				}
			}
		}

		// 최단 거리 찾기
		for (int idx = 0; idx <= breakCnt; idx++) {
			if (dist[boardH - 1][boardW - 1][idx] != NOT_VISITED) ans = Math.min(ans, dist[boardH - 1][boardW - 1][idx]);
		}
		if (ans == Integer.MAX_VALUE) ans = -1;

		// 출력
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}