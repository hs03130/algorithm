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

	static int boardW, boardH;
	static char[][] board;
	static int[][][] dist;
	static ArrayDeque<int[]> Q = new ArrayDeque<>();
	static int ans;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static final int X = 0, Y = 1, BROKEN = 2;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());

		board = new char[boardH][boardW];
		dist = new int[boardH][boardW][2];
		for (int row = 0; row < boardH; row++) {
			board[row] = br.readLine().toCharArray();
		}

		if (boardH == 1 && boardW == 1) {
			ans = 1;
		}
		
		dist[0][0][0] = 1;
		Q.offer(new int[] { 0, 0, 0 });
		while (!Q.isEmpty() && ans == 0) {
			int[] cur = Q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];

				if (nx == boardH - 1 && ny == boardW - 1) {
					ans = dist[cur[X]][cur[Y]][cur[BROKEN]] + 1;
					break;
				}

				if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW) {
					continue;
				}

				// 벽 부수고 이동
				if (board[nx][ny] == '1' && cur[BROKEN] == 0 && dist[nx][ny][1] == 0) {
					dist[nx][ny][1] = dist[cur[X]][cur[Y]][0] + 1;
					Q.offer(new int[] { nx, ny, 1 });
				} else if (board[nx][ny] == '0' && dist[nx][ny][cur[BROKEN]] == 0) {
					dist[nx][ny][cur[BROKEN]] = dist[cur[X]][cur[Y]][cur[BROKEN]] + 1;
					Q.offer(new int[] { nx, ny, cur[BROKEN] });
				}
			}
		}

		if (ans == 0) {
			sb.append(-1);
		} else {
			sb.append(ans);
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
