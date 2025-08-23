/*
 * 1. 입력
 *  1-1 영역 크기
 *  1-2 영상 정보
 *  
 * 2. 압축 -> 재귀적으로
 *  2-1 현재 영역의 색깔이 모두 같은지 확인
 *  2-2 다름 -> 4개로 나눠서 다시 재귀
 *  2-3 같음 -> 출력
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

	// 입력
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

	// 압축
	static void func(int size, int x, int y) {
		// 현재 영역의 색깔이 모두 같음
		if (check(size, x, y)) {
			// 출력
			sb.append(board[x][y]);
			return;
		}
		// 현재 영역의 색깔이 모두 같지 않음 ->
		// 4 영역으로 나눠서 확인하기
		sb.append('(');
		int half = size / 2;
		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 2; col++) {
				func(half, x + row * half, y + col * half);
			}
		}
		sb.append(')');
	}

	// 영역의 색깔이 모두 같은지 확인 같으면 true, 다르면 false
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