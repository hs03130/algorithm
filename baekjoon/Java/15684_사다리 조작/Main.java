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

	static int boardH, boardW, lineCnt;
	static int[][] board;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		boardW = Integer.parseInt(st.nextToken());
		lineCnt = Integer.parseInt(st.nextToken());
		boardH = Integer.parseInt(st.nextToken());

		board = new int[boardH][boardW];
		for (int lineIdx = 0; lineIdx < lineCnt; lineIdx++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			board[row - 1][col - 1] = 1;
		}

		bt(0, 0, 0);

		if (ans > 3) {
			ans = -1;
		}
		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bt(int r, int c, int selectCnt) {
		// 현재 상태에서 가능한지 확인
		if (check()) {
			ans = Math.min(ans, selectCnt);
			return;
		}

		// 더이상 놓을 수 없음
		if (selectCnt == 3) {
			return;
		}

		// 현재 지점에서 오른쪽으로 가로선을 그린다
		for (int row = r; row < boardH; row++) {
			for (int col = 0; col < boardW - 1; col++) {
				if (row == r && col < c) {
					continue;
				}
				// 왼쪽이랑 연속되는지 확인 & 이미 그려져있는지 확인
				if ((col == 0 || board[row][col - 1] == 0) && board[row][col] == 0) {
					board[row][col] = 1;
					if (col + 1 == boardW - 1) {
						bt(row + 1, 0, selectCnt + 1);
					} else {
						bt(row, col + 1, selectCnt + 1);
					}
					board[row][col] = 0;
				}
			}
		}
	}

	static boolean check() {
		for (int idx = 0; idx < boardW; idx++) {
			int col = idx;
			for (int row = 0; row < boardH; row++) {
				if (board[row][col] == 1) {
					col++;
				} else if (col > 0 && board[row][col - 1] == 1) {
					col--;
				}
			}

			if (col != idx) {
				return false;
			}
		}
		return true;
	}

}
