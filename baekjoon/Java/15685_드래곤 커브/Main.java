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

	static int curveCnt;
	static boolean[][] board;
	static ArrayDeque<Integer> Q;
	static int ans = 0;

	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };
	static final int BOARD_SIZE = 101, X = 0, Y = 1, DIR = 2;
	static final boolean DRAGON = true;

	// ���� ���� ���� ------------------------------------
	public static void main(String[] args) throws IOException {
		curveCnt = Integer.parseInt(br.readLine());
		board = new boolean[BOARD_SIZE][BOARD_SIZE];

		// curveCnt���� �巡�� Ŀ�긦 board�� �׸���
		for (int idx = 0; idx < curveCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()); // 0-based
			int x = Integer.parseInt(st.nextToken()); // 0-based
			int d = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());

			Q = new ArrayDeque<>();

			// ������
			board[x][y] = DRAGON;

			// 0���� �׸���
			int curX = x + dx[d];
			int curY = y + dy[d];
			board[curX][curY] = DRAGON;
			Q.offer(d);

			// 1������� target������� �׸���
			for (int t = 1; t <= target; t++) {
				ArrayDeque<Integer> tmpQ = new ArrayDeque<>();
				while (!Q.isEmpty()) {
					int curD = Q.poll();
					// curD�� �ð���� 90���� ������
					int nd = (curD + 1) % 4;
					
					// curX,curY���� nd�������� �̾� �׸���
					int nx = curX + dx[nd];
					int ny = curY + dy[nd];
					board[nx][ny] = DRAGON;
					
					tmpQ.offerLast(curD);
					tmpQ.offerFirst(nd);
					
					// �������� �̾ �׸���
					curX = nx;
					curY = ny;
				}
				Q = tmpQ;
			}
		}

		// �������� ��� DRAGON�� 1x1 ũ���� ���簢�� ���� ����
		for (int row = 0; row < BOARD_SIZE - 1; row++) {
			for (int col = 0; col < BOARD_SIZE - 1; col++) {
				if (board[row][col] == DRAGON && board[row][col + 1] == DRAGON && board[row + 1][col] == DRAGON
						&& board[row + 1][col + 1] == DRAGON) {
					ans++;
				}
			}
		}
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
