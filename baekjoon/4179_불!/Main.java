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
	static int startX, startY;
	static int[][] fire, dist;
	static ArrayDeque<int[]> Q = new ArrayDeque<>();
	static int ans;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static final int X = 0, Y = 1, DIST = 2;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());

		fire = new int[boardH][boardW];
		dist = new int[boardH][boardW];
		for (int row = 0; row < boardH; row++) {
			char[] line = br.readLine().toCharArray();
			for (int col = 0; col < boardW; col++) {
				if (line[col] == '#') {
					fire[row][col] = 0;
				} else if (line[col] == 'F') {
					fire[row][col] = 0;
					Q.offer(new int[] { row, col });
				} else if (line[col] == 'J') {
					startX = row;
					startY = col;
					fire[row][col] = -1;
				} else if (line[col] == '.') {
					fire[row][col] = -1;
					dist[row][col] = -1;
				}
			}
		}

		// 불 이동속도 구하기
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];

				if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW || fire[nx][ny] != -1) {
					continue;
				}

				fire[nx][ny] = fire[cur[X]][cur[Y]] + 1;
				Q.offer(new int[] { nx, ny });
			}
		}

		// 지훈이 이동
		Q.offer(new int[] { startX, startY, 0 });
		while (!Q.isEmpty() && ans == 0) {
			int[] cur = Q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];

				// 탈출 성공
				if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW) {
					ans = dist[cur[X]][cur[Y]] + 1;
					break;
				}

				// 불 번짐
				if (fire[nx][ny] != -1 && fire[nx][ny] <= dist[cur[X]][cur[Y]] + 1) {
					continue;
				}

				// 이미 지나감
				if (dist[nx][ny] != -1) {
					continue;
				}

				dist[nx][ny] = dist[cur[X]][cur[Y]] + 1;
				Q.offer(new int[] { nx, ny });
			}
		}

		if (ans == 0) {
			sb.append("IMPOSSIBLE");
		} else {
			sb.append(ans);
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
