import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static char[][] board;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		board = new char[N][N];
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				board[row][col] = ' ';
			}
		}

		solve(N, 0, 0);

		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				sb.append(board[row][col]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	static void solve(int n, int x, int y) {
		if (n == 1) {
			board[x][y] = '*';
			return;
		}

		int next = n / 3;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (row == 1 && col == 1)
					continue;
				solve(next, x + row * next, y + col * next);
			}
		}
	}

}
