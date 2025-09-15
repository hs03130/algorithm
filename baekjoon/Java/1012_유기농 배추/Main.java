import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int boardH;
	static int boardW;
	static int bugCnt;
	static int[][] board;
	static Deque<int[]> Q;

	static int ans;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static final int X = 0;
	static final int Y = 1;
	static final int GROUP = 2;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			// 입력
			st = new StringTokenizer(br.readLine());
	
			boardH = Integer.parseInt(st.nextToken());
			boardW = Integer.parseInt(st.nextToken());
			bugCnt = Integer.parseInt(st.nextToken());
	
			board = new int[boardH][boardW];
			Q = new ArrayDeque<>();
			for (int bug = 0; bug < bugCnt; bug++) {
				st = new StringTokenizer(br.readLine());
				board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = -1;
			}
	
			// 풀이
			ans = 0;
	
			for (int row = 0; row < boardH; row++) {
				for (int col = 0; col < boardW; col++) {
					// 시작점
					if (board[row][col] == -1) {
						board[row][col] = ++ans; // 군집별로 표시
	
						Q.offer(new int[] { row, col });
						while (!Q.isEmpty()) {
							int[] cur = Q.poll();
	
							for (int dir = 0; dir < 4; dir++) {
								int nx = cur[X] + dx[dir];
								int ny = cur[Y] + dy[dir];
	
								if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW || board[nx][ny] >= 0) {
									continue;
								}
								
								board[nx][ny] = ans;
								Q.offer(new int[] {nx, ny});
							}
						}
					}
				}
			}
			
	
			// 출력
			System.out.println(ans);
		}
	}

}