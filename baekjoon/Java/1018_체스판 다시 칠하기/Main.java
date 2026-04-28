import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int boardH;
	static int boardW;

	static String[] board;

	static int ans = Integer.MAX_VALUE;

	static final int BOARD_SIZE = 8;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());

		board = new String[boardH];
		for (int row = 0; row < boardH; row++) {
			board[row] = br.readLine();
		}

		// 시작점
		for (int x = 0; x <= boardH - BOARD_SIZE; x++) {
			for (int y = 0; y <= boardW - BOARD_SIZE; y++) {
				int startW = 0; // W로 시작했을 때 바꿀 횟수
				int startB = 0; // B로 시작했을 때 바꿀 횟수

				// 체스판 사이즈 8*8를 확인하면서 색 확인
				for (int row = 0; row < BOARD_SIZE; row++) {
					for (int col = 0; col < BOARD_SIZE; col++) {

						// 시작칸과 동일한 색깔
						if ((row % 2 == col % 2)) {
							if (board[x + row].charAt(y + col) == 'B') {
								startW++;
							} else {
								startB++;
							}
						} 
						// 시작칸과 다른 색깔
						else {
							if (board[x + row].charAt(y + col) == 'W') {
								startW++;
							} else {
								startB++;
							}
						}

					}
				}

				if (startW < ans)
					ans = startW;
				if (startB < ans)
					ans = startB;
			}
		}

		System.out.println(ans);
	}

}
