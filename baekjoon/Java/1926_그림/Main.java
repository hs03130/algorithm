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
	static int[][] board;
	static ArrayDeque<int[]> Q = new ArrayDeque<>();
	static int ansCnt = 0, ansMaxSize = 0;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static final int X = 0, Y = 1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());

		board = new int[boardH][boardW];
		for (int row = 0; row < boardH; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < boardW; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());

			}
		}

		for (int row = 0; row < boardH; row++) {
			for (int col = 0; col < boardW; col++) {
				if (board[row][col] == 1) {
					ansCnt++;

					int size = 1;
					board[row][col] = 0;
					Q.offer(new int[] { row, col });
					while (!Q.isEmpty()) {
						int[] cur = Q.poll();

						for (int dir = 0; dir < 4; dir++) {
							int nx = cur[X] + dx[dir];
							int ny = cur[Y] + dy[dir];

							if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW || board[nx][ny] == 0) {
								continue;
							}

							size++;
							board[nx][ny] = 0;
							Q.offer(new int[] { nx, ny });
						}

						ansMaxSize = Math.max(ansMaxSize, size);
					}
				}
			}
		}

		sb.append(ansCnt + "\n");
		sb.append(ansMaxSize + "\n");

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
