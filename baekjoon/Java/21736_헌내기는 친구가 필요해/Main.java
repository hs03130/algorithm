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
	static char[][] board;

	static int[] start;
	static Deque<int[]> Q;
	static boolean[][] visit;

	static int ans;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static final int X = 0;
	static final int Y = 1;
	static final char ME = 'I';
	static final char FRIEND = 'P';
	static final char WALL = 'X';
	static final char EMPTY = 'O';

	public static void main(String[] args) throws Exception {
		// 입력
		st = new StringTokenizer(br.readLine());
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());
		board = new char[boardH][boardW];

		for (int row = 0; row < boardH; row++) {
			board[row] = br.readLine().toCharArray();
			for (int col = 0; col < boardW; col++) {
				if (board[row][col] == ME) {
					start = new int[2];
					start[X] = row;
					start[Y] = col;
				}
			}
		}

		// 풀이
		ans = 0;

		visit = new boolean[boardH][boardW];
		Q = new ArrayDeque<>();
		
		visit[start[X]][start[Y]] = true;
		Q.offer(start);

		while (!Q.isEmpty()) {
			int[] cur = Q.poll();
			// 상하좌우로 이동
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];
				
				// 캠퍼스 밖
				if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW) {
					continue;
				}
				
				// 이미 방문 | 벽
				if (visit[nx][ny] || board[nx][ny] == WALL) {
					continue;
				}

				// 친구를 만남
				if (board[nx][ny] == FRIEND) {
					ans++;
				}

				visit[nx][ny] = true;
				Q.offer(new int[] { nx, ny });
			}
		}

		// 출력
		System.out.println(ans == 0 ? "TT" : ans);
	}

}