/*
 * 1. �Է�
 *  1-1 ���� ũ�� �Է�
 *  1-2 ���� �Է�
 * 2. BFS�� ���� ���� ���ϱ�
 *  2-1 ���ϻ����� �ƴ� ��� ���� ���ϱ� BFS
 *      �湮���� ���� ĭ = ���� ����
 *      ���� ���� �ø���,
 *      ���� ������ �ش��ϴ� ĭ�� ��� �湮 ó��
 *      ���� ���� ���� ã��
 *  2-2 ���ϻ����� ����� ���� ���� ���� ���ϱ�
 *      �ʷϻ��� ���������� �ٲ��� BFS
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int boardSize;
	static int[][] board;
	static boolean[][] visited; // �湮 ����
	static Deque<int[]> Q;
	static int ans1; // ���ϻ����� �ƴ� ���
	static int ans2; // ���ϻ����� ���

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int X = 0;
	static int Y = 1;

	// �Է�
	static void input() throws Exception {
		boardSize = Integer.parseInt(br.readLine().trim());
		board = new int[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			String str = br.readLine().trim();
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = str.charAt(col);
			}
		}
	}

	// ���� ������ �湮 ó�� ���ֱ�
	static void bfs(int startX, int startY) {
		Q = new ArrayDeque<>();
		// �������������� ���.
		visited[startX][startY] = true;
		Q.offer(new int[] { startX, startY });
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();
			// ���� ĭ �湮�ϱ�
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];
				// ���� ������ �Ѿ�� ��� ����
				if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize) {
					continue;
				}
				// �̹� �湮�߰ų� �̵��� �� ���� ĭ�� ��� ����
				if (visited[nx][ny] || board[cur[X]][cur[Y]] != board[nx][ny]) {
					continue;
				}
				// �湮 ó�� ���ְ� ����
				visited[nx][ny] = true;
				Q.offer(new int[] { nx, ny });
			}
		}
	}

	static void solve() {
		// ���ϻ����� �ƴ� ���
		ans1 = 0;
		visited = new boolean[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				// ���ο� ���� ����
				if (!visited[row][col]) {
					ans1++; // ���� ���� ����
					bfs(row, col); // ���� ���� �湮 ó��
				}
			}
		}

		// ���ϻ����� ���
		ans2 = 0;
		visited = new boolean[boardSize][boardSize];
		// ���� ������ �ʷ�/������ �������� ���ϹǷ� �ʷ��� ���������� �ٲ���
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				if (board[row][col] == 'G') {
					board[row][col] = 'R';
				}
			}
		}
		for(int row=0; row<boardSize; row++) {
			for(int col=0; col<boardSize; col++) {
				// ���ο� ���� ����
				if (!visited[row][col]) {
					ans2++; // ���� ���� ����
					bfs(row, col); // ���� ���� �湮 ó��
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		input();
		solve();
		System.out.println(ans1 + " " + ans2);
	}

}