/*
 * 치즈가 없는 가장자리 (0,0)부터 시작하여 상하좌우로 모든 공기를 방문한다.
 * 치즈를 만나면 공기와 접촉한 치즈이고, 녹아 없어질 치즈이다.
 * 녹을 치즈를 다른 곳에 저장해둔다.
 * 모든 공기를 방문했다면 저장해둔 치즈를 녹인다. -> 한시간
 * 
 * 치즈가 녹으면 다시 공기가 된것이다.
 * 다시 새로 생긴 공기를 모두 방문(치즈가 녹아서 생겼거나, 구멍이 공기에 노출되면서 생긴)
 * 위 작업을 t시간동안 반복하며 모든 치즈가 녹게 되었다면
 * 마지막으로 저장해둔 녹을 치즈가 모두 녹아 없어지기 전 치즈 개수이다. 
 * 모두 녹기까지 걸린 시간은 t시간이다.
 * 
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int rowSize;
	static int colSize;
	static int[][] board;

	static Deque<int[]> air; // 공기
	static Deque<int[]> removeCheeze; // 녹을 치즈
	static boolean[][] visited;
	static int cheezeCnt; // 초기 상태의 치즈 개수
	static int removeCheezeCnt; // 녹은 치즈
	static int ansTime; // 모두 녹는데 걸리는 시간
	static int ansCnt; // 모두 녹기 전 마지막에 남은 치즈 개수

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static final int X = 0;
	static final int Y = 1;

	// 입력
	static void input() throws Exception {
		// 보드 크기 입력
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		// 보드 정보 입력
		board = new int[rowSize][colSize];
		cheezeCnt = 0;
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				// 초기 상태의 치즈 개수 계산
				if (board[row][col] == 1) {
					cheezeCnt++;
				}
			}
		}
	}

	static void solve() throws Exception {
		air = new ArrayDeque<>();
		removeCheeze = new ArrayDeque<>();
		visited = new boolean[rowSize][colSize];

		air.offer(new int[] { 0, 0 }); // 판의 가장자리에는 치즈 없음
		
		// 모두 녹을때까지 작업 반복
		removeCheezeCnt = 0;
		for (int t = 1;; t++) {
			// 모든 공기 방문
			while (!air.isEmpty()) {
				int[] cur = air.poll();
				// 상하 좌우로 인접한 칸 확인
				for (int dir = 0; dir < 4; dir++) {
					int nx = cur[X] + dx[dir];
					int ny = cur[Y] + dy[dir];
					if (nx < 0 || nx >= rowSize || ny < 0 || ny >= colSize || visited[nx][ny]) {
						continue;
					}

					// 공기와 접촉한 치즈
					if (board[nx][ny] == 1) {
						// 녹을 치즈는 따로 저장해두기
						removeCheeze.offer(new int[] { nx, ny });
						removeCheezeCnt++;
					} 
					// 공기
					else {
						air.offer(new int[] { nx, ny });
					}
					
					visited[nx][ny] = true;
				}
			}
			
			// 치즈가 모두 녹게 됨
			if (removeCheezeCnt == cheezeCnt) {
				ansTime = t;
				ansCnt = removeCheeze.size();
				return;
			}
			
			// 녹을 치즈 처리
			while (!removeCheeze.isEmpty()) {
				// 치즈를 녹인다.
				int[] cur = removeCheeze.poll();
				board[cur[X]][cur[Y]] = 0;
				// 치즈가 녹은 자리는 공기가 되었다 -> 이어서
				air.offer(cur);
			}
		}

	}

	public static void main(String[] args) throws Exception {
		input();
		solve();
		System.out.println(ansTime);
		System.out.println(ansCnt);
	}

}