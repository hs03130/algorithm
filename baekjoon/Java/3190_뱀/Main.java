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

	static int boardSize, appleCnt, cmdCnt;
	static int[][] board;
	static int[] cmdTime;
	static char[] cmdDir;
	static ArrayDeque<int[]> Q;
	static int time = 0, dir = 0, cmdIdx = 0;

	// 오른쪽, 아래, 왼쪽, 위
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static final int EMPTY = 0, APPLE = 1, SNAKE = 2;
	static final int X = 0, Y = 1;

	public static void main(String[] args) throws IOException {
		// 보드 크기
		boardSize = Integer.parseInt(br.readLine());
		board = new int[boardSize][boardSize];

		// 사과 위치 입력
		appleCnt = Integer.parseInt(br.readLine());
		for (int idx = 0; idx < appleCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = APPLE;
		}

		// 뱀 이동 시간과 방향 입력
		cmdCnt = Integer.parseInt(br.readLine());
		cmdTime = new int[cmdCnt];
		cmdDir = new char[cmdCnt];
		for (int idx = 0; idx < cmdCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			cmdTime[idx] = Integer.parseInt(st.nextToken()); // 오른차순으로 입력
			cmdDir[idx] = st.nextToken().charAt(0);
		}

		Q = new ArrayDeque<>();
		Q.offer(new int[] { 0, 0 });
		while (!Q.isEmpty()) {
			time++;

			// 머리를 다음칸에 위치
			int[] head = Q.peekFirst();
			int nx = head[X] + dx[dir];
			int ny = head[Y] + dy[dir];

			// 벽이나 자기 몸에 부딪히면 종료
			if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize || board[nx][ny] == SNAKE) {
				break;
			}

			Q.offerFirst(new int[] { nx, ny });

			// 이동한 칸에 사과가 존재
			if (board[nx][ny] == APPLE) {
				board[nx][ny] = SNAKE;
				// 꼬리 그대로
			} else {
				board[nx][ny] = SNAKE;
				// 꼬리 앞으로
				int[] tail = Q.pollLast();
				board[tail[X]][tail[Y]] = EMPTY;
			}

			// 이동 종료 후 방향 변경 확인
			if (cmdIdx < cmdCnt && cmdTime[cmdIdx] == time) {
				// 왼쪽으로 90도 회전
				if (cmdDir[cmdIdx] == 'L') {
					dir--;
					if (dir == -1)
						dir = 3;
				}
				// 오른쪽으로 90도 회전
				else {
					dir++;
					if (dir == 4)
						dir = 0;
				}
				cmdIdx++;
			}
		}

		sb.append(time);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
