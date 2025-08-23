import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[][] board;
	static int[] cnt = new int[3];

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		board = new int[N][N];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		func(N, 0, 0);

		System.out.println(cnt[0]);
		System.out.println(cnt[1]);
		System.out.println(cnt[2]);
	}

	static void func(int len, int r, int c) {

		int type = board[r][c];

		if (len == 1) {
			cnt[1 + type]++;
			return;
		}

		boolean f = true;
		for (int row = r; row < r + len; row++) {
			for (int col = c; col < c + len; col++) {
				if (board[row][col] != type) {
					f = false;
				}
			}
		}

		if (f) {
			cnt[1 + type]++;
		} else {
			int nextLen = len / 3;
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					func(len / 3, r + row * nextLen, c + col * nextLen);
				}
			}
		}

	}
}
