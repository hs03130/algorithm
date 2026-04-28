import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M, B;
	static int board[][];

	static int curTime;
	static int curInventoryBlock;

	static int ansTime = Integer.MAX_VALUE;
	static int ansHeight = 0;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		B = Integer.parseInt(st.nextToken()); // 인벤토리 블록

		board = new int[N][M];

		int maxHeight = 0;
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, board[row][col]);
			}
		}

		for (int height = maxHeight; height >= 0; height--) {
			curTime = 0;
			curInventoryBlock = B;
			
			// 모든 좌표를 돌면서 해당 좌표를 height 높이로 만드는데 걸리는 작업 시간 계산
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					if (board[row][col] > height) { // 목표 height 보다 높으면 
						curInventoryBlock += board[row][col] - height; // 블록을 제거하고 인벤토리에 넣는다
						curTime += (board[row][col] - height) * 2; // 블록 한 개당 작업시간 2초
					} else if (board[row][col] < height) { // 목표 height 보다 낮으면
						curInventoryBlock -= height - board[row][col]; // 인벤토리에서 블록을 꺼내 쌓는다.
						curTime += height - board[row][col]; // 블록 한 개당 작업시간 1초
					}
				}
			}

			if (curInventoryBlock < 0) continue; // 현재 인벤토리에 있는 블록 개수가 음수라면 불가능한 높이

			if (ansTime > curTime) {
				ansTime = curTime;
				ansHeight = height;
			}
		}

		System.out.println(ansTime + " " + ansHeight);
	}
}
