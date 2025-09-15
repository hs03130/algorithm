/*
 * 1. 입력
 *  1-1 보드 크기 입력
 *  1-2 보드 입력
 * 2. BFS로 영역 개수 구하기
 *  2-1 적록색약이 아닌 사람 먼저 구하기 BFS
 *      방문하지 않은 칸 = 영역 시작
 *      영역 개수 늘리고,
 *      같은 영역에 해당하는 칸을 모두 방문 처리
 *      다음 영역 시작 찾기
 *  2-2 적록색약인 사람이 보는 영역 개수 구하기
 *      초록색을 빨강색으로 바꾼후 BFS
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int boardSize;
	static int[][] board;
	static boolean[][] visited; // 방문 여부
	static Deque<int[]> Q;
	static int ans1; // 적록색약이 아닌 경우
	static int ans2; // 적록색약인 경우

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int X = 0;
	static int Y = 1;

	// 입력
	static void input() throws Exception {
		boardSize = Integer.parseInt(br.readLine().trim());
		board = new int[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			String str = br.readLine().trim();
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = str.charAt(col);
			}
		}
	}

	// 같은 영역을 방문 처리 해주기
	static void bfs(int startX, int startY) {
		Q = new ArrayDeque<>();
		// 시작점에서부터 출발.
		visited[startX][startY] = true;
		Q.offer(new int[] { startX, startY });
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();
			// 인접 칸 방문하기
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];
				// 보드 범위를 넘어가는 경우 제외
				if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize) {
					continue;
				}
				// 이미 방문했거나 이동할 수 없는 칸인 경우 제외
				if (visited[nx][ny] || board[cur[X]][cur[Y]] != board[nx][ny]) {
					continue;
				}
				// 방문 처리 해주고 진행
				visited[nx][ny] = true;
				Q.offer(new int[] { nx, ny });
			}
		}
	}

	static void solve() {
		// 적록색약이 아닌 사람
		ans1 = 0;
		visited = new boolean[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				// 새로운 영역 시작
				if (!visited[row][col]) {
					ans1++; // 영역 개수 증가
					bfs(row, col); // 같은 영역 방문 처리
				}
			}
		}

		// 적록색약인 사람
		ans2 = 0;
		visited = new boolean[boardSize][boardSize];
		// 적록 색약은 초록/빨강을 구분하지 못하므로 초록을 빨강색으로 바꿔줌
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				if (board[row][col] == 'G') {
					board[row][col] = 'R';
				}
			}
		}
		for(int row=0; row<boardSize; row++) {
			for(int col=0; col<boardSize; col++) {
				// 새로운 영역 시작
				if (!visited[row][col]) {
					ans2++; // 영역 개수 증가
					bfs(row, col); // 같은 영역 방문 처리
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		input();
		solve();
		System.out.println(ans1 + " " + ans2);
	}

}