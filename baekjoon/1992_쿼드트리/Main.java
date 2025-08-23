/*
 * 1. �Է�
 *  1-1 ���� ũ��
 *  1-2 ���� ����
 *  
 * 2. ���� -> ���������
 *  2-1 ���� ������ ������ ��� ������ Ȯ��
 *  2-2 �ٸ� -> 4���� ������ �ٽ� ���
 *  2-3 ���� -> ���
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	static StringTokenizer st;
	static StringBuilder sb;

	static int boardSize;
	static int[][] board;

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
				board[row][col] = str.charAt(col) - '0';
			}
		}
	}

	static void solve() {
		sb = new StringBuilder();
		func(boardSize, 0, 0);
	}

	// ����
	static void func(int size, int x, int y) {
		// ���� ������ ������ ��� ����
		if (check(size, x, y)) {
			// ���
			sb.append(board[x][y]);
			return;
		}
		// ���� ������ ������ ��� ���� ���� ->
		// 4 �������� ������ Ȯ���ϱ�
		sb.append('(');
		int half = size / 2;
		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 2; col++) {
				func(half, x + row * half, y + col * half);
			}
		}
		sb.append(')');
	}

	// ������ ������ ��� ������ Ȯ�� ������ true, �ٸ��� false
	static boolean check(int size, int x, int y) {
		for (int row = x; row < x + size; row++) {
			for (int col = y; col < y + size; col++) {
				if (board[x][y] != board[row][col])
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		input();
		solve();
		System.out.println(sb.toString());
	}

}