import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int boardSize; // 색종이 크기
	static int[][] board;
	static int[] ans;

	public static void main(String[] args) throws Exception {
		// 입력
		boardSize = Integer.parseInt(br.readLine());
		board = new int[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		// 풀이
		ans = new int[2];
		func(0, 0, boardSize);
		
		
		// 출력
		System.out.println(ans[0]);
		System.out.println(ans[1]);
	}

	static void func(int startX, int startY, int len) {
		// 1칸 짜리 색종이
		if (len == 1) {
			ans[board[startX][startY]] += 1;
			return;
		}

		// 색종이 색이 모두 같은지 확인
		int color = allSame(startX, startY, len);

		// 색종이 색이 모두 같음
		if (color != -1) {
			ans[color]++;
		} 
		// 색종이 색깔이 다름 -> 절반 나눠서 다시 확인
		else {
			int half = len / 2;
			func(startX, startY, half);
			func(startX + half, startY, half);
			func(startX, startY + half, half);
			func(startX + half, startY + half, half);
		}
	}

	// 해당 범위의 색종이 색깔이 모두 같은지 확인
	// 같으면 색깔 같지 않으면 -1 반환
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