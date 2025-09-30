import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 행과 열은 따로 판단한다
 * 행 열 함수를 구분해서 풀었지만, 열을 행처럼 새로 만들어서 공통 함수로 만들 수 도 있다.
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
				// 왼쪽에서 오른쪽으로 가면서 길 확인 && 높이가 다르면 경사로를 놓을 수 있는지 확인
				if (flagR && board[row][col] != board[row][col + 1]) {
					flagR = checkR(row, col);
				}

				// 위쪽에서 아래로 가면서 길 확인 && 높이가 다르면 경사로를 놓을 수 있는지 확인
				if (flagC && board[col][row] != board[col + 1][row]) {
					flagC = checkC(col, row);
				}

				// 높이 같음 -> 계속 확인
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
		// 높이 차이 1초과
		if (Math.abs(board[row][col] - board[row][col + 1]) > 1) {
			return false;
		}
		// 오른쪽으로 내려가는 경사로 놓을 수 있는지 확인
		else if (board[row][col] == board[row][col + 1] + 1) {
			// 길이 부족
			if (col + blockLen >= boardSize) {
				return false;
			}

			for (int k = 1; k <= blockLen; k++) {
				// 이미 경사로가 있거나, 높이 다름
				if (fixed[ROW][col + k] || board[row][col + k] != board[row][col] - 1) {
					return false;
				}
				fixed[ROW][col + k] = true;
			}
		}
		// 오른쪽으로 올라가는 경사로 놓을 수 있는지 확인
		else if (board[row][col] == board[row][col + 1] - 1) {
			// 길이 부족
			if (col - blockLen + 1 < 0) {
				return false;
			}
			for (int k = 0; k < blockLen; k++) {
				// 이미 경사로가 있거나, 높이 다름
				if (fixed[ROW][col - k] || board[row][col - k] != board[row][col + 1] - 1) {
					return false;
				}
				fixed[ROW][col - k] = true;
			}
		}
		return true;
	}

	static boolean checkC(int row, int col) {
		// 높이 차이 1초과
		if (Math.abs(board[row][col] - board[row + 1][col]) > 1) {
			return false;
		}
		// 아래쪽으로 내려가는 경사로 놓을 수 있는지 확인
		else if (board[row][col] == board[row + 1][col] + 1) {
			// 길이 부족
			if (row + blockLen >= boardSize) {
				return false;
			}

			for (int k = 1; k <= blockLen; k++) {
				// 이미 경사로가 있거나, 높이 다름
				if (fixed[COL][row + k] || board[row + k][col] != board[row][col] - 1) {
					return false;
				}
				fixed[COL][row + k] = true;
			}
		}
		// 아래쪽으로 올라가는 경사로 놓을 수 있는지 확인
		else if (board[row][col] == board[row + 1][col] - 1) {
			// 길이 부족
			if (row - blockLen + 1 < 0) {
				return false;
			}
			for (int k = 0; k < blockLen; k++) {
				// 이미 경사로가 있거나, 높이 다름
				if (fixed[COL][row - k] || board[row - k][col] != board[row + 1][col] - 1) {
					return false;
				}
				fixed[COL][row - k] = true;
			}
		}
		return true;
	}
}
