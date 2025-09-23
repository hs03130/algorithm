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

	static int boardH, boardW;
	static char[][] board;

	// ���� ����, �Ķ� ����, ����, Ƚ��
	static ArrayDeque<int[]> Q;
	static int redX, redY, blueX, blueY;

	static int ans = -1;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static final int RED_X = 0, RED_Y = 1, BLUE_X = 2, BLUE_Y = 3, CNT = 4;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());
		board = new char[boardH][];
		for (int row = 0; row < boardH; row++) {
			board[row] = br.readLine().toCharArray();
			for (int col = 0; col < boardW; col++) {
				if (board[row][col] == 'R') {
					redX = row;
					redY = col;
				}
				if (board[row][col] == 'B') {
					blueX = row;
					blueY = col;
				}
			}
		}

		Q = new ArrayDeque<>();
		Q.offer(new int[] { redX, redY, blueX, blueY, 0 });

		while (!Q.isEmpty() && ans == -1) {
			int[] cur = Q.poll();
			if (cur[CNT] >= 10) {
				break;
			}

			// �����¿� �������� ���� �̵�
			for (int dir = 0; dir < 4; dir++) {
				// ���� ���� ���� �̵�
				if (firstMoveColor(dir, cur[RED_X], cur[RED_Y], cur[BLUE_X], cur[BLUE_Y]) > 0) {
					moveRedFirst(cur, dir);
				}
				// �Ķ� ���� ���� �̵�
				else {
					moveBlueFirst(cur, dir);
				}
				if (ans != -1)
					break;
			}
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void moveRedFirst(int[] cur, int dir) {
		boolean exitRed = false, exitBlue = false;

		// ���� ���� �̵�
		int rx = cur[RED_X];
		int ry = cur[RED_Y];
		while (true) {
			if (board[rx + dx[dir]][ry + dy[dir]] == '#') { // �����ڸ��� ��� # �̹Ƿ� ���� üũ �ʿ����
				break; // ��ֹ�. ����
			}
			if (board[rx + dx[dir]][ry + dy[dir]] == 'O') {
				exitRed = true; // ���� ���� ����
				rx = 0;
				ry = 0;
				break;
			}
			rx += dx[dir];
			ry += dy[dir];
		}
		// �Ķ� ���� �̵�
		int bx = cur[BLUE_X];
		int by = cur[BLUE_Y];
		while (true) {
			if (board[bx + dx[dir]][by + dy[dir]] == '#') {
				break; // ��ֹ�. ����
			}
			if (bx + dx[dir] == rx && by + dy[dir] == ry) {
				break; // �տ� ���� ������ ����
			}
			if (board[bx + dx[dir]][by + dy[dir]] == 'O') {
				exitBlue = true; // �Ķ� ���� ����
				break;
			}
			bx += dx[dir];
			by += dy[dir];
		}

		// ���� ������ Ż��
		if (exitRed && !exitBlue) {
			ans = cur[CNT] + 1;
		}
		// �̵����� ����
		else if (cur[RED_X] == rx && cur[RED_Y] == ry && cur[BLUE_X] == bx && cur[BLUE_Y] == by) {
			return;
		}
		// �̵� �Ϸ�
		else if (!exitRed && !exitBlue) {
			Q.offer(new int[] { rx, ry, bx, by, cur[CNT] + 1 });
		}
	}

	static void moveBlueFirst(int[] cur, int dir) {
		boolean exitRed = false, exitBlue = false;

		// �Ķ� ���� �̵�
		int bx = cur[BLUE_X];
		int by = cur[BLUE_Y];
		while (true) {
			if (board[bx + dx[dir]][by + dy[dir]] == '#') {
				break; // ��ֹ�. ����
			}
			if (board[bx + dx[dir]][by + dy[dir]] == 'O') {
				exitBlue = true; // �Ķ� ���� ����
				bx = 0;
				by = 0;
				break;
			}
			bx += dx[dir];
			by += dy[dir];
		}

		// ���� ���� �̵�
		int rx = cur[RED_X];
		int ry = cur[RED_Y];
		while (true) {
			if (board[rx + dx[dir]][ry + dy[dir]] == '#') { // �����ڸ��� ��� # �̹Ƿ� ���� üũ �ʿ����
				break; // ��ֹ�. ����
			}
			if (rx + dx[dir] == bx && ry + dy[dir] == by) {
				break; // �տ� �Ķ� ������ ����
			}
			if (board[rx + dx[dir]][ry + dy[dir]] == 'O') {
				exitRed = true; // ���� ���� ����
				break;
			}
			rx += dx[dir];
			ry += dy[dir];
		}

		// ���� ������ Ż��
		if (exitRed && !exitBlue) {
			ans = cur[CNT] + 1;
		}
		// �̵����� ����
		else if (cur[RED_X] == rx && cur[RED_Y] == ry && cur[BLUE_X] == bx && cur[BLUE_Y] == by) {
			return;
		}
		// �̵� �Ϸ�
		else if (!exitRed && !exitBlue) {
			Q.offer(new int[] { rx, ry, bx, by, cur[CNT] + 1 });
		}
	}

	// ��ȯ���� ����� ���� ���� ����, ��ȯ���� ������ �Ķ� ���� ���� �����δ�.
	static int firstMoveColor(int dir, int rx, int ry, int bx, int by) {
		// �Ʒ�
		if (dir == 0) {
			return rx - bx;
		}
		// ������
		else if (dir == 1) {
			return ry - by;
		}
		// ��
		else if (dir == 2) {
			return bx - rx;
		}
		// ����
		else {
			return by - ry;
		}
	}
}
