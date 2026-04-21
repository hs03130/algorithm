import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int boardSize, switchCnt;
	static boolean[][] board, visited;
	static ArrayList<int[]>[] switchList;
	static ArrayDeque<int[]> Q;
	static int ans = 1;

	static final int X = 0, Y = 1, BOARD_IDX = 2;
	static final int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	static boolean isOutOfBound(int x, int y) {
		return (x < 0 || x >= boardSize || y < 0 || y >= boardSize);
	}

	public static void main(String[] args) throws IOException {
		// 보드 크기, 플레이어 수 입력
		st = new StringTokenizer(br.readLine());
		boardSize = Integer.parseInt(st.nextToken());
		switchCnt = Integer.parseInt(st.nextToken());

		board = new boolean[boardSize][boardSize];
		visited = new boolean[boardSize][boardSize];
		switchList = new ArrayList[boardSize * boardSize];

		// 스위치 입력
		for (int idx = 0; idx < switchCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int boardIdx = (Integer.parseInt(st.nextToken()) - 1) * boardSize + Integer.parseInt(st.nextToken()) - 1;
			if (switchList[boardIdx] == null) switchList[boardIdx] = new ArrayList<>();
			switchList[boardIdx].add(new int[] { (Integer.parseInt(st.nextToken()) - 1), (Integer.parseInt(st.nextToken()) - 1) });
		}

		Q = new ArrayDeque<>();
		board[0][0] = true;
		visited[0][0] = true;
		Q.offer(new int[] { 0, 0, 0 });

		// bfs
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();

			// 스위치 확인
			if (switchList[cur[BOARD_IDX]] != null) {
				while (!switchList[cur[BOARD_IDX]].isEmpty()) {
					int[] swc = switchList[cur[BOARD_IDX]].remove(0);
					// 불 켜기
					if (!board[swc[X]][swc[Y]]) {
						board[swc[X]][swc[Y]] = true;
						ans++;

						// 해당 칸이 이동 가능한 칸인지 확인
						for (int dir = 0; dir < 4; dir++) {
							int nx = swc[X] + dx[dir];
							int ny = swc[Y] + dy[dir];
							if (isOutOfBound(nx, ny) || !visited[nx][ny]) continue;

							// 현재 이동 가능한 칸과 인접해 있음 -> 이동 가능
							visited[swc[X]][swc[Y]] = true;
							Q.offer(new int[] { swc[X], swc[Y], swc[X] * boardSize + swc[Y] });
							break;
						}
					}
				}
			}

			// 인접한 칸 중 이동 가능한 칸 찾기
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[X] + dx[dir];
				int ny = cur[Y] + dy[dir];
				if (isOutOfBound(nx, ny)) continue;

				// 불이 꺼져있거나 이미 방문함
				if (!board[nx][ny] || visited[nx][ny]) continue;

				// 불이 켜져있고, 처음 이동하는 칸
				visited[nx][ny] = true;
				Q.offer(new int[] { nx, ny, nx * boardSize + ny });
			}
		}

		// 출력
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}