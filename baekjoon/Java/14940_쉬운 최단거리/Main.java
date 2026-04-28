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
	static int[][] board;
	static int[][] dist;
	static Deque<int[]> Q;
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static final int X = 0;
	static final int Y = 1;
	static final int DIST = 2;
	
	public static void main(String[] args) throws Exception {
		// 입력
		st = new StringTokenizer(br.readLine());

		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());

		board = new int[boardH][boardW];
		Q = new ArrayDeque<>();
		for(int row=0; row<boardH; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0; col<boardW; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				// 출발
				if (board[row][col] == 2) {
					Q.offer(new int[] {row, col, 0});
				}
			}
		}
		
		// 풀이
		dist = new int[boardH][boardW];
		while(!Q.isEmpty()) {
			int[] cur = Q.poll();
			
			for(int dir=0; dir<4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];
				
				if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW) {
					continue;
				}
				if (board[nx][ny] != 1 || dist[nx][ny] > 0) {
					continue;
				}
				
				dist[nx][ny] = cur[DIST] + 1;
				Q.offer(new int[] {nx, ny, dist[nx][ny]});
			}
		}
		
		// 출력
		for(int row=0; row<boardH; row++) {
			for(int col=0; col<boardW; col++) {
				if (board[row][col] == 1 && dist[row][col] == 0) {
					System.out.print("-1 ");
				} else {
					System.out.print(dist[row][col] + " ");
				}
			}
			System.out.println();
		}
	}

}