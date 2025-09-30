import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * ��� ���� ���� �Ǵ��Ѵ�
 * �� �� �Լ��� �����ؼ� Ǯ������, ���� ��ó�� ���� ���� ���� �Լ��� ���� �� �� �ִ�.
 */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int boardSize, blockLen;
	static int[][] board;
	static boolean[][] fixed;
	static int ans = 0;

	static final int ROW = 0, COL = 1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		boardSize = Integer.parseInt(st.nextToken());
		blockLen = Integer.parseInt(st.nextToken());
		board = new int[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		fixed = new boolean[2][boardSize];

		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				fixed[ROW][col] = false;
				fixed[COL][col] = false;
			}

			boolean flagR = true;
			boolean flagC = true;
			for (int col = 0; col < boardSize - 1; col++) {
				// ���ʿ��� ���������� ���鼭 �� Ȯ�� && ���̰� �ٸ��� ���θ� ���� �� �ִ��� Ȯ��
				if (flagR && board[row][col] != board[row][col + 1]) {
					flagR = checkR(row, col);
				}

				// ���ʿ��� �Ʒ��� ���鼭 �� Ȯ�� && ���̰� �ٸ��� ���θ� ���� �� �ִ��� Ȯ��
				if (flagC && board[col][row] != board[col + 1][row]) {
					flagC = checkC(col, row);
				}

				// ���� ���� -> ��� Ȯ��
			}
			if (flagR) {
				ans++;
			}
			if (flagC) {
				ans++;
			}
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static boolean checkR(int row, int col) {
		// ���� ���� 1�ʰ�
		if (Math.abs(board[row][col] - board[row][col + 1]) > 1) {
			return false;
		}
		// ���������� �������� ���� ���� �� �ִ��� Ȯ��
		else if (board[row][col] == board[row][col + 1] + 1) {
			// ���� ����
			if (col + blockLen >= boardSize) {
				return false;
			}

			for (int k = 1; k <= blockLen; k++) {
				// �̹� ���ΰ� �ְų�, ���� �ٸ�
				if (fixed[ROW][col + k] || board[row][col + k] != board[row][col] - 1) {
					return false;
				}
				fixed[ROW][col + k] = true;
			}
		}
		// ���������� �ö󰡴� ���� ���� �� �ִ��� Ȯ��
		else if (board[row][col] == board[row][col + 1] - 1) {
			// ���� ����
			if (col - blockLen + 1 < 0) {
				return false;
			}
			for (int k = 0; k < blockLen; k++) {
				// �̹� ���ΰ� �ְų�, ���� �ٸ�
				if (fixed[ROW][col - k] || board[row][col - k] != board[row][col + 1] - 1) {
					return false;
				}
				fixed[ROW][col - k] = true;
			}
		}
		return true;
	}

	static boolean checkC(int row, int col) {
		// ���� ���� 1�ʰ�
		if (Math.abs(board[row][col] - board[row + 1][col]) > 1) {
			return false;
		}
		// �Ʒ������� �������� ���� ���� �� �ִ��� Ȯ��
		else if (board[row][col] == board[row + 1][col] + 1) {
			// ���� ����
			if (row + blockLen >= boardSize) {
				return false;
			}

			for (int k = 1; k <= blockLen; k++) {
				// �̹� ���ΰ� �ְų�, ���� �ٸ�
				if (fixed[COL][row + k] || board[row + k][col] != board[row][col] - 1) {
					return false;
				}
				fixed[COL][row + k] = true;
			}
		}
		// �Ʒ������� �ö󰡴� ���� ���� �� �ִ��� Ȯ��
		else if (board[row][col] == board[row + 1][col] - 1) {
			// ���� ����
			if (row - blockLen + 1 < 0) {
				return false;
			}
			for (int k = 0; k < blockLen; k++) {
				// �̹� ���ΰ� �ְų�, ���� �ٸ�
				if (fixed[COL][row - k] || board[row - k][col] != board[row + 1][col] - 1) {
					return false;
				}
				fixed[COL][row - k] = true;
			}
		}
		return true;
	}
}
