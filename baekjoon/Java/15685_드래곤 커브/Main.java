import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int curveCnt;
	static boolean[][] board;
	static ArrayDeque<Integer> Q;
	static int ans = 0;

	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };
	static final int BOARD_SIZE = 101, X = 0, Y = 1, DIR = 2;
	static final boolean DRAGON = true;

	// 메인 실행 로직 ------------------------------------
	public static void main(String[] args) throws IOException {
		curveCnt = Integer.parseInt(br.readLine());
		board = new boolean[BOARD_SIZE][BOARD_SIZE];

		// curveCnt개의 드래곤 커브를 board에 그린다
		for (int idx = 0; idx < curveCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()); // 0-based
			int x = Integer.parseInt(st.nextToken()); // 0-based
			int d = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());

			Q = new ArrayDeque<>();

			// 시작점
			board[x][y] = DRAGON;

			// 0세대 그리기
			int curX = x + dx[d];
			int curY = y + dy[d];
			board[curX][curY] = DRAGON;
			Q.offer(d);

			// 1세대부터 target세대까지 그리기
			for (int t = 1; t <= target; t++) {
				ArrayDeque<Integer> tmpQ = new ArrayDeque<>();
				while (!Q.isEmpty()) {
					int curD = Q.poll();
					// curD를 시계방향 90도로 돌리기
					int nd = (curD + 1) % 4;
					
					// curX,curY에서 nd방향으로 이어 그리기
					int nx = curX + dx[nd];
					int ny = curY + dy[nd];
					board[nx][ny] = DRAGON;
					
					tmpQ.offerLast(curD);
					tmpQ.offerFirst(nd);
					
					// 끝점부터 이어서 그린다
					curX = nx;
					curY = ny;
				}
				Q = tmpQ;
			}
		}

		// 꼭짓점이 모두 DRAGON인 1x1 크기의 정사각형 개수 세기
		for (int row = 0; row < BOARD_SIZE - 1; row++) {
			for (int col = 0; col < BOARD_SIZE - 1; col++) {
				if (board[row][col] == DRAGON && board[row][col + 1] == DRAGON && board[row + 1][col] == DRAGON
						&& board[row + 1][col + 1] == DRAGON) {
					ans++;
				}
			}
		}
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
