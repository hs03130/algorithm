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

	static int boardH, boardW, startX, startY, cmdCnt;
	static int[][] board;
	static int[] moveDir;
	static int[] dice = { 0, 0, 0, 0, 0, 0 };

	// �� �� �� ��
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	static final int TOP = 0, BOTTOM = 5, RIGHT = 2, LEFT = 3, FRONT = 1, BACK = 4;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		cmdCnt = Integer.parseInt(st.nextToken());

		board = new int[boardH][boardW];
		for (int row = 0; row < boardH; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < boardW; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		moveDir = new int[cmdCnt];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < cmdCnt; idx++) {
			moveDir[idx] = Integer.parseInt(st.nextToken()) - 1;
		}

		int x = startX;
		int y = startY;
		for (int idx = 0; idx < cmdCnt; idx++) {
			int dir = moveDir[idx];

			// ���� �̵��� ĭ
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			// ���� ���� ���� �����Ѵ�
			if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW) {
				continue;
			}

			// �ֻ��� �̵�
			moveDice(dir);

			// �ֻ��� ��� ���
			sb.append(dice[TOP] + "\n");

			// ����� ����
			if (board[nx][ny] == 0) {
				board[nx][ny] = dice[BOTTOM];
			}
			// �ֻ����� ���� �� ����� 0����
			else {
				dice[BOTTOM] = board[nx][ny];
				board[nx][ny] = 0;
			}

			x = nx;
			y = ny;
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void moveDice(int dir) {
		int tmp = dice[TOP];

		// ����
		if (dir == 0) {
			dice[TOP] = dice[LEFT];
			dice[LEFT] = dice[BOTTOM];
			dice[BOTTOM] = dice[RIGHT];
			dice[RIGHT] = tmp;
		}
		// ����
		else if (dir == 1) {
			dice[TOP] = dice[RIGHT];
			dice[RIGHT] = dice[BOTTOM];
			dice[BOTTOM] = dice[LEFT];
			dice[LEFT] = tmp;
		}
		// ����
		else if (dir == 2) {
			dice[TOP] = dice[BACK];
			dice[BACK] = dice[BOTTOM];
			dice[BOTTOM] = dice[FRONT];
			dice[FRONT] = tmp;
		}
		// ����
		else {
			dice[TOP] = dice[FRONT];
			dice[FRONT] = dice[BOTTOM];
			dice[BOTTOM] = dice[BACK];
			dice[BACK] = tmp;
		}
	}
}
