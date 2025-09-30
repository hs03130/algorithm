import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int boardH, boardW, x, y, dir;
	static int[][] board;
	static int ans = 0;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static final int X = 0, Y = 1;
	static final int DIRTY = 0, WALL = 1, CLEAN = 2;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());

		board = new int[boardH][boardW];
		for (int row = 0; row < boardH; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < boardW; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			// ���� ĭ�� û�ҵ��� ���� ��� ���� ĭ�� û���Ѵ�
			if (board[x][y] == DIRTY) {
				board[x][y] = CLEAN;
				ans++;
			}

			// �ֺ� 4ĭ �� û�ҵ��� ���� ĭ�� ����
			boolean move = false;
			int tmpDir = dir;
			for (int idx = 0; idx < 4; idx++) {
				// �ݽð�������� 90�� ȸ��
				tmpDir--;
				if (tmpDir == -1)
					tmpDir = 3;

				// ��ĭ ���� Ȯ��
				int nx = x + dx[tmpDir];
				int ny = y + dy[tmpDir];
				if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW) {
					continue;
				}

				// ��ĭ�� û�ҵǾ� ���� ������ �̵�
				if (board[nx][ny] == DIRTY) {
					x = nx;
					y = ny;
					dir = tmpDir;
					move = true;
					break;
				}
			}

			// �ֺ� 4ĭ�� ��� û�ҵǾ� ����
			if (!move) {
				// ��ĭ ���� Ȯ��
				int nx = x - dx[dir];
				int ny = y - dy[dir];
				// ���̶� ������ �� ������ �۵��� �����
				if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW || board[nx][ny] == WALL) {
					break;
				}

				// �����Ѵ�
				x = nx;
				y = ny;
			}
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
