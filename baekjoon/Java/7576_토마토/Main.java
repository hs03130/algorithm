import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int boardH;
	static int boardW;

	static int[][] board;
	static int[][] dist;
	static Deque<int[]> Q;

	static int ans;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static final int X = 0;
	static final int Y = 1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		boardW = Integer.parseInt(st.nextToken());
		boardH = Integer.parseInt(st.nextToken());

		board = new int[boardH][boardW];
		dist = new int[boardH][boardW];
		Q = new ArrayDeque<>();

		ans = 0;

		for (int row = 0; row < boardH; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < boardW; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());

				if (board[row][col] == 1) { // ���� �丶��
					Q.offer(new int[] { row, col }); // BFS�� ���۵� �������� �̸� �ֱ�
				}

				if (board[row][col] == 0) { // ������ �丶�� �ڸ��� �湮�ϱ�
					dist[row][col] = -1;
				}
			}
		}

		while (!Q.isEmpty()) {
			int[] cur = Q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];

				if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW) {
					continue;
				}

				if (dist[nx][ny] >= 0) {
					continue;
				}

				dist[nx][ny] = dist[cur[X]][cur[Y]] + 1;
				Q.offer(new int[] { nx, ny });

			}
		}

		for (int row = 0; row < boardH; row++) {
			for (int col = 0; col < boardW; col++) {
				if (dist[row][col] == -1) { // ���� ���� �丶�䰡 ����
					ans = -1;
					System.out.println(ans);
					return;
				}

				ans = Math.max(ans, dist[row][col]); // �丶�䰡 ��� �ʹµ� �ɸ��� ��¥
				// ��� �丶�䰡 �̹� �;� ������ ans�� 0
			}
		}

		System.out.println(ans);
	}

}
