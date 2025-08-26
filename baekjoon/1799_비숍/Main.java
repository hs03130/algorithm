
/**
 * 1. 비숍을 놓을 수 있는지 판단할 때 같은 색 칸들만 서로 영향을 준다.
 *    흰색칸에 최대한 놓은 개수와 검정색칸에 최대한 놓은 개수를 더한다. 
 * 2. N-Queen 문제에서 row별로 퀸이 있는지 저장한 것 처럼 
 * 	    오른쪽 대각선, 왼쪽 대각선에 비숍이 놓여있는지 저장
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

		// 다음 같은 색 칸
		int nx = r;
		int ny = c + 2;
		if (ny >= boardSize) {
			nx++;
			ny = nx % 2 == color ? 0 : 1;
		}

		// 놓을 수 있는지 확인
		if (board[r][c] == EMPTY && !leftLine[r + c] && !rightLine[r - c + boardSize - 1]) {
			// 놓고 넘어감
			leftLine[r + c] = rightLine[r - c + boardSize - 1] = true;
			bt(color, nx, ny, cnt + 1);
			
			leftLine[r + c] = rightLine[r - c + boardSize - 1] = false;
		}

		// 안놓고 넘어감
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

		// 흰색, 0,0 칸
		bt(0, 0, 0, 0);

		// 검정색, 0,1칸
		bt(1, 0, 1, 0);

		sb.append(ans[0] + ans[1]);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}