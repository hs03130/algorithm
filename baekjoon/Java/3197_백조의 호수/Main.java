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
	static StringTokenizer st;

	static int boardH, boardW, birdIdx = 0;
	static int[][] bird;
	static char[][] board;
	static boolean[][] visited;
	static ArrayDeque<int[]> Q, BQ;
	static boolean finished = false;
	static int ans;

	static final char WATER = '.', ICE = 'X', BIRD = 'L', FIRST = '0', SECOND = '1';
	static final int X = 0, Y = 1;
	static final int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	static boolean outOfBound(int x, int y) {
		return (x < 0 || x >= boardH || y < 0 || y >= boardW);
	}

	static void checkBQ() {
		ArrayDeque<int[]> tmpBQ = new ArrayDeque<>();
		while (!BQ.isEmpty()) {
			int[] cur = BQ.poll();
			boolean nearIce = false;
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];
				if (outOfBound(nx, ny)) continue;

				if (board[nx][ny] == WATER) {
					board[nx][ny] = board[cur[X]][cur[Y]];
					BQ.offer(new int[] { nx, ny });
				} else if (board[nx][ny] == ICE) {
					nearIce = true;
				} else if (board[nx][ny] != board[cur[X]][cur[Y]]) {
					finished = true;
					return;
				}
			}
			if (nearIce) tmpBQ.offer(new int[] { cur[X], cur[Y] });
		}
		BQ = tmpBQ;
		return;
	}

	public static void main(String[] args) throws IOException {
		bird = new int[2][2]; // 백조 위치

		st = new StringTokenizer(br.readLine());
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());

		// 호수 입력
		board = new char[boardH][boardW];
		visited = new boolean[boardH][boardW];
		for (int row = 0; row < boardH; row++) {
			board[row] = br.readLine().toCharArray();
			for (int col = 0; col < boardW; col++) {
				if (board[row][col] == BIRD) {
					bird[birdIdx][X] = row;
					bird[birdIdx][Y] = col;
					board[row][col] = (char) ('0' + birdIdx++);
				}
			}
		}

		Q = new ArrayDeque<>(); // 물 확장용
		BQ = new ArrayDeque<>(); // 백조가 있는 물 표시용

		// 백조가 있는 물 표시
		visited[bird[0][X]][bird[0][Y]] = true;
		visited[bird[1][X]][bird[1][Y]] = true;
		BQ.offer(new int[] { bird[0][X], bird[0][Y] });
		BQ.offer(new int[] { bird[1][X], bird[1][Y] });
		checkBQ();

		// 물 BFS 시작점
		for (int row = 0; row < boardH; row++) {
			for (int col = 0; col < boardW; col++) {
				if (board[row][col] == ICE) continue;
				visited[row][col] = true;
				Q.offer(new int[] { row, col });
			}
		}

		while (!finished && !Q.isEmpty()) {
			ArrayDeque<int[]> tmpQ = new ArrayDeque<>();
			while (!Q.isEmpty()) {
				int[] cur = Q.poll();
				for (int dir = 0; dir < 4; dir++) {
					int nx = cur[X] + dx[dir];
					int ny = cur[Y] + dy[dir];
					if (outOfBound(nx, ny) || visited[nx][ny]) continue;

					if (board[nx][ny] == ICE) {
						board[nx][ny] = board[cur[X]][cur[Y]];
						visited[nx][ny] = true;
						tmpQ.offer(new int[] { nx, ny });
						if (board[cur[X]][cur[Y]] != WATER) BQ.offer(new int[] { nx, ny });
					}
				}
			}
			Q = tmpQ;
			checkBQ();
			ans++;
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}