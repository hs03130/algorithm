/*
 * R*C 격자가 있다.
 * C[0]부터 시작해서  C[size-1]로 간다.
 * 이때 건물이 있는 곳은 통과할 수 없고, 1시 3시 5시 방향으로만 갈 수 있다.
 * C[0]과 C[size-1]에는 건물이 없다.
 * 가능한 경로가 최대가 되도록 경로를 만들고, 경로 개수를 출력한다.
 * 단 경로끼리 겹치면 안된다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int rowSize; // 1~10000
	static int colSize; // 5~500
	static char[][] board;

	static int[] dx = { -1, 0, 1 };

	static int ans;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		// 보드 크기 입력
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		// 보드 정보 입력
		board = new char[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			board[row] = br.readLine().toCharArray(); // . 또는 X
		}
	}

	static void solve() {
		// 행 마다 파이프 설치가 가능한지 확인
		for (int row = 0; row < rowSize; row++) {
			func(row, 0);
		}
	}

	static boolean func(int curRow, int curCol) {
		// 파이프 연결함
		if (curCol == colSize - 1) {
			ans++;
			return true;
		}

		board[curRow][curCol] = 'x';

		// 세방향에 대해 탐색
		// 1시
		if (curRow > 0 && board[curRow - 1][curCol + 1] == '.') {
			if (func(curRow - 1, curCol + 1)) {
				return true;
			}
		}
		// 3시
		if (board[curRow][curCol + 1] == '.') {
			if (func(curRow, curCol + 1)) {
				return true;
			}
		}
		// 5시
		if (curRow < rowSize - 1 && board[curRow + 1][curCol + 1] == '.') {
			if (func(curRow + 1, curCol + 1)) {
				return true;
			}
		}
		
		// 세 방향 다 진행 불가
		return false;
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(ans);
	}

}