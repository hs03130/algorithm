import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int x, y, z;
	static char[][][] board;
	static int[][][] dist;
	static Deque<int[]> Q;
	static int[] start, end;

	static int[] dx = { 1, 0, -1, 0, 0, 0 };
	static int[] dy = { 0, 1, 0, -1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, 1, -1 };

	static int Z = 0, X = 1, Y = 2;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		z = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());

		while (x != 0 && y != 0 && z != 0) {
			board = new char[z][x][y];
			dist = new int[z][x][y];

			// ют╥б
			for (int depth = 0; depth < z; depth++) {
				for (int row = 0; row < x; row++) {
					board[depth][row] = br.readLine().toCharArray();
					for (int col = 0; col < y; col++) {
						if (board[depth][row][col] == 'S') {
							start = new int[] { depth, row, col };
						} else if (board[depth][row][col] == 'E') {
							end = new int[] { depth, row, col };
							dist[depth][row][col] = -1;
						} else if (board[depth][row][col] == '.') {
							dist[depth][row][col] = -1;
						}
					}
				}
				br.readLine();
			}

			Q = new ArrayDeque<>();
			Q.offer(start);

			while (!Q.isEmpty() && dist[end[Z]][end[X]][end[Y]] == -1) {
				int[] cur = Q.pop();
				for (int dir = 0; dir < 6; dir++) {
					int nz = cur[Z] + dz[dir];
					int nx = cur[X] + dx[dir];
					int ny = cur[Y] + dy[dir];

					if (nz < 0 || nz >= z || nx < 0 || nx >= x || ny < 0 || ny >= y) {
						continue;
					}

					if (dist[nz][nx][ny] > -1) {
						continue;
					}

					dist[nz][nx][ny] = dist[cur[Z]][cur[X]][cur[Y]] + 1;
					Q.offer(new int[] { nz, nx, ny });
				}
			}

			if (dist[end[Z]][end[X]][end[Y]] == -1) {
				System.out.println("Trapped!");
			} else {
				System.out.println("Escaped in " + dist[end[Z]][end[X]][end[Y]] + " minute(s).");
			}

			st = new StringTokenizer(br.readLine());
			z = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
		}

	}

}
