import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int boardSize; // ������ ũ��
	static int[][] board;
	static int[] ans;

	public static void main(String[] args) throws Exception {
		// �Է�
		boardSize = Integer.parseInt(br.readLine());
		board = new int[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		// Ǯ��
		ans = new int[2];
		func(0, 0, boardSize);
		
		
		// ���
		System.out.println(ans[0]);
		System.out.println(ans[1]);
	}

	static void func(int startX, int startY, int len) {
		// 1ĭ ¥�� ������
		if (len == 1) {
			ans[board[startX][startY]] += 1;
			return;
		}

		// ������ ���� ��� ������ Ȯ��
		int color = allSame(startX, startY, len);

		// ������ ���� ��� ����
		if (color != -1) {
			ans[color]++;
		} 
		// ������ ������ �ٸ� -> ���� ������ �ٽ� Ȯ��
		else {
			int half = len / 2;
			func(startX, startY, half);
			func(startX + half, startY, half);
			func(startX, startY + half, half);
			func(startX + half, startY + half, half);
		}
	}

	// �ش� ������ ������ ������ ��� ������ Ȯ��
	// ������ ���� ���� ������ -1 ��ȯ
	static int allSame(int startX, int startY, int len) {
		int color = board[startX][startY];
		for (int row = 0; row < len; row++) {
			for (int col = 0; col < len; col++) {
				if (board[startX + row][startY + col] != color) {
					return -1;
				}
			}
		}

		return color;
	}

}