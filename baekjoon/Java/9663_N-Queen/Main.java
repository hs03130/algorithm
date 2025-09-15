import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int boardSize;
	static int[] queen;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {

		boardSize = Integer.parseInt(br.readLine());
		queen = new int[boardSize];

		bt(0);

		System.out.println(cnt);
	}

	static void bt(int r) {
		if (r == boardSize) {
			cnt++;
			return;
		}

		for (int idx = 0; idx < boardSize; idx++) {
			queen[r] = idx; // r열의 idx행에 퀸 놓기
			if (check(r)) {
				bt(r + 1);
			}
		}
	}

	static boolean check(int r) {
		for (int idx = 0; idx < r; idx++) {
			// 세로 혹은 대각선
			if (queen[idx] == queen[r] || Math.abs(queen[r] - queen[idx]) == Math.abs(r - idx)) {
				return false;
			}
		}
		return true;
	}

}
