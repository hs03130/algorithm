/**
 * 25���� 7���� �����ϴ� ��� ������ �õ�
 * 25C7 = 480,700
 * ������ 7���� ���� ���η� �������ִ��� BFS�� �̿��Ͽ� Ȯ��
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static char[][] board;
	static int[] selected = new int[7];

	static int ans = 0;
	
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static final int X = 0, Y = 1;

	static void bt(int k, int cntY) {
		if (cntY > 3) // Y�� 3�� ����
			return;

		if (k == 7) { // ĥ���� ���� �Ϸ�, BFS�� ������ �ִ��� Ȯ��
			boolean[][] isPrincess = new boolean[5][5];
			boolean[][] visited = new boolean[5][5];
			int visitCnt = 0;
			
			Deque<int[]> Q = new ArrayDeque<>();
			for (int idx = 0; idx < 7; idx++) {
				int x = selected[idx] / 5, y = selected[idx] % 5;
				isPrincess[x][y] = true;
				if (Q.isEmpty()) {
					visited[x][y] = true;
					visitCnt++;
					Q.offer(new int[] { x, y });
				}
			}

			while (!Q.isEmpty() && visitCnt < 7) {
				int[] cur = Q.pop();
				for (int dir = 0; dir < 4; dir++) {
					int nx = cur[X] + dx[dir];
					int ny = cur[Y] + dy[dir];

					if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
						continue;
					}
					if (!isPrincess[nx][ny] || visited[nx][ny]) {
						continue;
					}

					visited[nx][ny] = true;
					visitCnt++;
					Q.offer(new int[] { nx, ny });
				}
			}

			if (visitCnt == 7) // S�� 4�� �̻��� ĥ���ָ� �����߰�, ��� ������ ����
				ans++;
			return;
		}

		// ���� ���� ����
		for (int idx = k == 0 ? 0 : selected[k - 1] + 1; idx < 25; idx++) {
			selected[k] = idx;

			if (board[idx / 5][idx % 5] == 'S')
				bt(k + 1, cntY);
			else
				bt(k + 1, cntY + 1);
		}
	}

	public static void main(String[] args) throws IOException {

		board = new char[5][];
		for (int row = 0; row < 5; row++) {
			board[row] = br.readLine().toCharArray();
		}

		bt(0, 0);

		System.out.println(ans);
	}

}
