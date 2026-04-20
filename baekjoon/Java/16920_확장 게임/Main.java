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

	static int boardH, boardW, playerCnt;
	static int[] dist, score;
	static int[][] board;
	static boolean canExpand = true;
	static ArrayDeque<Castle>[] Q;

	static final int EMPTY = -1, WALL = -2;
	static final int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	static class Castle {
		int x, y, step;

		public Castle(int x, int y, int step) {
			this.x = x;
			this.y = y;
			this.step = step;
		}
	}

	public static void main(String[] args) throws IOException {
		// 보드 크기, 플레이어 수 입력
		st = new StringTokenizer(br.readLine());
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());
		playerCnt = Integer.parseInt(st.nextToken());

		board = new int[boardH][boardW];
		dist = new int[playerCnt];
		score = new int[playerCnt];
		Q = new ArrayDeque[playerCnt];

		// 플레이어별 이동 거리 입력
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < playerCnt; idx++) {
			dist[idx] = Integer.parseInt(st.nextToken());
			Q[idx] = new ArrayDeque<>();
		}

		// 게임판 초기 상태 입력
		for (int row = 0; row < boardH; row++) {
			String line = br.readLine();
			for (int col = 0; col < boardW; col++) {
				char ch = line.charAt(col);
				if (ch == '.') board[row][col] = EMPTY;
				else if (ch == '#') board[row][col] = WALL;
				else {
					board[row][col] = ch - '0' - 1;
					score[board[row][col]]++;
					Q[board[row][col]].offer(new Castle(row, col, dist[board[row][col]]));
				}
			}
		}

		// 모든 플레이어가 더 이상 확장할 수 없을 때까지 게임 진행
		while (canExpand) {
			canExpand = false;
			
			// 1번 플레이어부터 차례대로 확장
			for (int player = 0; player < playerCnt; player++) {
				ArrayDeque<Castle> tmpQ = new ArrayDeque<>(); // 다음 라운드에 사용될 큐
				
				while (!Q[player].isEmpty()) {
					Castle cur = Q[player].poll();

					for (int dir = 0; dir < 4; dir++) {
						int nx = cur.x + dx[dir];
						int ny = cur.y + dy[dir];

						if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW || board[nx][ny] != EMPTY) continue;
						
						board[nx][ny] = player;
						score[player]++;

						// 확장 횟수 남음 -> 이번 라운드에서 이어서 확장
						if (cur.step > 1) Q[player].offer(new Castle(nx, ny, cur.step - 1));
						// 확장 끝 -> 다음 라운드에서 다시 시작
						else tmpQ.offer(new Castle(nx, ny, dist[player]));
					}
				}
				
				// 확장 가능한 칸이 남아있음 -> 다음 라운드 진행
				if (!tmpQ.isEmpty()) canExpand = true;
				
				Q[player] = tmpQ;
			}
		}

		// 출력
		for (int idx = 0; idx < playerCnt; idx++) {
			sb.append(score[idx] + " ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}