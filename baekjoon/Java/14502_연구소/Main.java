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

	static int boardH, boardW;
	static int[][] originBoard;
	static int[][] virusXY;
	static int virusCnt = 0;
	static int ans = 0;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static final int EMPTY = 0, WALL = 1, VIRUS = 2;
	static final int X = 0, Y = 1;

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());
		originBoard = new int[boardH][boardW];
		virusXY = new int[10][2];

		for (int row = 0; row < boardH; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < boardW; col++) {
				originBoard[row][col] = Integer.parseInt(st.nextToken());
				if (originBoard[row][col] == VIRUS) {
					virusXY[virusCnt] = new int[] { row, col };
					virusCnt++;
				}
			}
		}

		// 벽을 세울 3칸 구하기 (꼭 3개를 세워야 한다)
		bt(0, 0);

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bt(int k, int selectCnt) {
		// 벽을 세울 곳을 모두 고름
		if (selectCnt == 3) {
			// 바이러스를 퍼지고, 남은 빈 칸 개수 세기
			bfs();
			return;
		}

		if (k == boardH * boardW) {
			return;
		}

		int row = k / boardW;
		int col = k % boardW;
		if (originBoard[row][col] == EMPTY) {
			// 벽을 세운다
			originBoard[row][col] = WALL;
			bt(k + 1, selectCnt + 1);
			originBoard[row][col] = EMPTY;
		}
		// 벽을 세우지 않는다
		bt(k + 1, selectCnt);
	}

	static void bfs() {
		// 원본 보드 복사
		int[][] board = new int[boardH][boardW];
		for (int row = 0; row < boardH; row++) {
			board[row] = originBoard[row].clone();
		}
		
		// 바이러스 위치 추가
		ArrayDeque<int[]> Q = new ArrayDeque<>();
		for (int idx = 0; idx < virusCnt; idx++) {
			Q.offer(new int[] { virusXY[idx][X], virusXY[idx][Y] });
		}

		// 바이러스 유출
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];

				if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW) {
					continue;
				}

				if (board[nx][ny] == EMPTY) {
					board[nx][ny] = VIRUS;
					Q.offer(new int[] { nx, ny });
				}
			}
		}

		// 안정 영역 개수 구하기
		int cnt = 0;
		for (int row = 0; row < boardH; row++) {
			for (int col = 0; col < boardW; col++) {
				if (board[row][col] == EMPTY) {
					cnt++;
				}
			}
		}

		if (cnt > ans) {
			ans = cnt;
		}
	}
}
