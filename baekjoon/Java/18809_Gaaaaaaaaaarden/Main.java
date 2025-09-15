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

	static int boardH, boardW, greenCnt, redCnt;
	static int[][] board;
	static ArrayDeque<int[]> Q;

	static int potionLandCnt = 0;
	static int[] element, selectG, selectR;
	static boolean[] isUsed;

	static int ans = 0;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static final int WATER = 0, LAND = 1, POTION_LAND = 2, GREEN = 3, RED = 4, FLOWER = -1;
	static final int X = 0, Y = 1, COLOR = 2;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());
		greenCnt = Integer.parseInt(st.nextToken());
		redCnt = Integer.parseInt(st.nextToken());

		board = new int[boardH][boardW];
		element = new int[boardH * boardW];
		for (int row = 0; row < boardH; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < boardW; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());

				if (board[row][col] == POTION_LAND) {
					element[potionLandCnt] = row * boardW + col;
					potionLandCnt++;
					board[row][col] = LAND;
				}
			}
		}

		// 배양액을 뿌릴 수 있는 땅중에서 배양액을 뿌릴 땅 고르기
		selectG = new int[greenCnt];
		selectR = new int[redCnt];
		isUsed = new boolean[potionLandCnt];

		bt(0, 0, 0, 0);

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bt(int g, int selectCntG, int r, int selectCntR) {
		// 빨간색까지 모두 선택
		if (selectCntR == redCnt) {
			// 선택한 토양에 배양액을 뿌렸을 때 결과 확인
			bfs();
			return;
		}

		// 초록색 선택 완료, 빨간색 선택
		if (selectCntG == greenCnt) {
			for (int idx = g; idx < potionLandCnt; idx++) {
				if (!isUsed[idx]) {
					selectR[selectCntR] = element[idx];
					isUsed[idx] = true;
					bt(idx + 1, selectCntG, r, selectCntR + 1);
					isUsed[idx] = false;
				}
			}
		}
		// 초록색 선택
		else {
			for (int idx = r; idx < potionLandCnt; idx++) {
				if (!isUsed[idx]) {
					selectG[selectCntG] = element[idx];
					isUsed[idx] = true;
					bt(g, selectCntG + 1, idx + 1, selectCntR);
					isUsed[idx] = false;
				}
			}
		}
	}

	static void bfs() {
		int[][] distG = new int[boardH][boardW];
		int[][] distR = new int[boardH][boardW];

		Q = new ArrayDeque<>();

		// 배양액 초기 설정
		for (int idx = 0; idx < greenCnt; idx++) {
			int row = selectG[idx] / boardW;
			int col = selectG[idx] % boardW;
			distG[row][col] = 1;
			Q.offer(new int[] { row, col, GREEN });
		}
		for (int idx = 0; idx < redCnt; idx++) {
			int row = selectR[idx] / boardW;
			int col = selectR[idx] % boardW;
			distR[row][col] = 1;
			Q.offer(new int[] { row, col, RED });
		}

		int flowerCnt = 0;
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();

			if (cur[COLOR] == GREEN && distG[cur[X]][cur[Y]] == FLOWER) {
				continue;
			}

			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];

				if (nx < 0 || nx >= boardH || ny < 0 || ny >= boardW || board[nx][ny] == WATER) {
					continue;
				}

				if (cur[COLOR] == GREEN) {
					if (distG[nx][ny] == 0) {
						distG[nx][ny] = distG[cur[X]][cur[Y]] + 1;
						Q.offer(new int[] { nx, ny, GREEN });
					}
				} else {
					if (distR[nx][ny] == 0) {
						// 초록색 배양액과 동시에 퍼짐
						if (distG[nx][ny] == distR[cur[X]][cur[Y]] + 1) {
							distG[nx][ny] = FLOWER;
							distR[nx][ny] = FLOWER;
							flowerCnt++;
						} else {
							distR[nx][ny] = distR[cur[X]][cur[Y]] + 1;
							Q.offer(new int[] { nx, ny, RED });
						}
					}
				}

			}
		}

		if (flowerCnt > ans) {
			ans = flowerCnt;
		}
	}
}