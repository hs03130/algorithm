/*
 * 최단 경로
 * 
 * 1. 입력
 *  1-1 보드 크기 : 0이면 종료
 *  1-2 보드 정보
 * 2. 최단 경로 Dijstra
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int boardSize;
	static int[][] board;
	static int[][] distance;
	static boolean[][] visited;
	static PriorityQueue<int[]> PQ;
	static int ans;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[] START = { 0, 0 };
	static int[] END;
	static int X = 0;
	static int Y = 1;
	static int COST = 2;

	// 입력
	static void input() throws IOException {
		board = new int[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		END = new int[] { boardSize - 1, boardSize - 1 };
	}

	// Dijstra
	static void solve() {
		distance = new int[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			Arrays.fill(distance[row], Integer.MAX_VALUE);
		}
		visited = new boolean[boardSize][boardSize];
		PQ = new PriorityQueue<>((o1, o2) -> o1[COST] - o2[COST]);

		// 시작점부터 출발
		distance[START[X]][START[Y]] = board[START[X]][START[Y]];
		PQ.offer(new int[] { START[X], START[Y], distance[START[X]][START[Y]] });

		while (!PQ.isEmpty()) {
			int[] min = PQ.poll();
			if (visited[min[X]][min[Y]]) {
				continue;
			}

			// 도착
			visited[min[X]][min[Y]] = true;
			if (min[X] == END[X] && min[Y] == END[Y]) {
				ans = min[COST]; // 출발칸부터 도착칸으로 오는데 드는 최소 비용
				return;
			}

			// 인접한 칸으로 가는 최소 비용 계산
			for (int dir = 0; dir < 4; dir++) {
				int nx = min[X] + dx[dir];
				int ny = min[Y] + dy[dir];
				if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize) {
					continue;
				}
				if (visited[nx][ny]) {
					continue;
				}
				distance[nx][ny] = Math.min(distance[nx][ny], min[COST] + board[nx][ny]);
				PQ.offer(new int[] { nx, ny, distance[nx][ny] });
			}
		}
		ans = -1;
	}

	public static void main(String args[]) throws IOException {
		int test_case = 0;
		boardSize = Integer.parseInt(br.readLine().trim());
		while (boardSize != 0) {
			input();
			solve();
			System.out.println("Problem " + ++test_case + ": " + ans);
			boardSize = Integer.parseInt(br.readLine().trim());
		}
	}

}