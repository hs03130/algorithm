
/**
 * 1. ����� ���� �� �ִ��� �Ǵ��� �� ���� �� ĭ�鸸 ���� ������ �ش�.
 *    ���ĭ�� �ִ��� ���� ������ ������ĭ�� �ִ��� ���� ������ ���Ѵ�. 
 * 2. N-Queen �������� row���� ���� �ִ��� ������ �� ó�� 
 * 	    ������ �밢��, ���� �밢���� ����� �����ִ��� ����
 */
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

	static int boardSize;
	static int[][] board;
	static boolean[] leftLine, rightLine;

	static int[] ans = { 0, 0 };

	static final int EMPTY = 1, WHITE = 0, BLACK = 1;

	static void bt(int color, int r, int c, int cnt) {
		if (r >= boardSize || c >= boardSize) {
			if (cnt > ans[color]) {
				ans[color] = cnt;
			}
			return;
		}

		// ���� ���� �� ĭ
		int nx = r;
		int ny = c + 2;
		if (ny >= boardSize) {
			nx++;
			ny = nx % 2 == color ? 0 : 1;
		}

		// ���� �� �ִ��� Ȯ��
		if (board[r][c] == EMPTY && !leftLine[r + c] && !rightLine[r - c + boardSize - 1]) {
			// ���� �Ѿ
			leftLine[r + c] = rightLine[r - c + boardSize - 1] = true;
			bt(color, nx, ny, cnt + 1);
			
			leftLine[r + c] = rightLine[r - c + boardSize - 1] = false;
		}

		// �ȳ��� �Ѿ
		bt(color, nx, ny, cnt);

	}

	public static void main(String[] args) throws IOException {
		boardSize = Integer.parseInt(br.readLine());
		board = new int[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		leftLine = new boolean[2 * boardSize - 1];
		rightLine = new boolean[2 * boardSize - 1];

		// ���, 0,0 ĭ
		bt(0, 0, 0, 0);

		// ������, 0,1ĭ
		bt(1, 0, 1, 0);

		sb.append(ans[0] + ans[1]);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}