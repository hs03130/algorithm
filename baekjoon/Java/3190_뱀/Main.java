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

	static int boardSize, appleCnt, cmdCnt;
	static int[][] board;
	static int[] cmdTime;
	static char[] cmdDir;
	static ArrayDeque<int[]> Q;
	static int time = 0, dir = 0, cmdIdx = 0;

	// ������, �Ʒ�, ����, ��
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static final int EMPTY = 0, APPLE = 1, SNAKE = 2;
	static final int X = 0, Y = 1;

	public static void main(String[] args) throws IOException {
		// ���� ũ��
		boardSize = Integer.parseInt(br.readLine());
		board = new int[boardSize][boardSize];

		// ��� ��ġ �Է�
		appleCnt = Integer.parseInt(br.readLine());
		for (int idx = 0; idx < appleCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = APPLE;
		}

		// �� �̵� �ð��� ���� �Է�
		cmdCnt = Integer.parseInt(br.readLine());
		cmdTime = new int[cmdCnt];
		cmdDir = new char[cmdCnt];
		for (int idx = 0; idx < cmdCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			cmdTime[idx] = Integer.parseInt(st.nextToken()); // ������������ �Է�
			cmdDir[idx] = st.nextToken().charAt(0);
		}

		Q = new ArrayDeque<>();
		Q.offer(new int[] { 0, 0 });
		while (!Q.isEmpty()) {
			time++;

			// �Ӹ��� ����ĭ�� ��ġ
			int[] head = Q.peekFirst();
			int nx = head[X] + dx[dir];
			int ny = head[Y] + dy[dir];

			// ���̳� �ڱ� ���� �ε����� ����
			if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize || board[nx][ny] == SNAKE) {
				break;
			}

			Q.offerFirst(new int[] { nx, ny });

			// �̵��� ĭ�� ����� ����
			if (board[nx][ny] == APPLE) {
				board[nx][ny] = SNAKE;
				// ���� �״��
			} else {
				board[nx][ny] = SNAKE;
				// ���� ������
				int[] tail = Q.pollLast();
				board[tail[X]][tail[Y]] = EMPTY;
			}

			// �̵� ���� �� ���� ���� Ȯ��
			if (cmdIdx < cmdCnt && cmdTime[cmdIdx] == time) {
				// �������� 90�� ȸ��
				if (cmdDir[cmdIdx] == 'L') {
					dir--;
					if (dir == -1)
						dir = 3;
				}
				// ���������� 90�� ȸ��
				else {
					dir++;
					if (dir == 4)
						dir = 0;
				}
				cmdIdx++;
			}
		}

		sb.append(time);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
