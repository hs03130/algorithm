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

	static int boardH, boardW;
	static int[][] board, dist;
	static ArrayDeque<int[]> Q;

	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
	static final int X = 0, Y = 1, INF = 100000;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		boardW = Integer.parseInt(st.nextToken());
		boardH = Integer.parseInt(st.nextToken());

		board = new int[boardH][boardW];
		dist = new int[boardH][boardW];
		for (int row = 0; row < boardH; row++) {
			char[] line = br.readLine().toCharArray();
			for (int col = 0; col < boardW; col++) {
				board[row][col] = line[col] - '0';
			}
			Arrays.fill(dist[row], INF);
		}
		dist[0][0] = 0;

		Q = new ArrayDeque<>();
		Q.offer(new int[] { 0, 0 });

		while (!Q.isEmpty()) {
			int[] cur = Q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];

				if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW) continue;
				if (dist[nx][ny] <= dist[cur[X]][cur[Y]] + board[nx][ny]) continue;
				dist[nx][ny] = dist[cur[X]][cur[Y]] + board[nx][ny];
				Q.offer(new int[] { nx, ny });
			}
		}

		bw.write(String.valueOf(dist[boardH - 1][boardW - 1]));
		bw.flush();
		bw.close();
	}

}
