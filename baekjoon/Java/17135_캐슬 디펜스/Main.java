/*
 * 1. 입력
 *  1-1 세로 크기(3~15) | 가로 크기(3~15) | 공격 범위 (1~10)
 *  1-2 적 위치
 * 
 * 2. 최대 공격수 찾기
 *  2-1 궁수를 배치하기 colSize P 3
 *  2-2 0초부터 공격하기 시작하여 1초마다 적은 한칸씩 아래로 내려온다 -> 궁수를 한칸 올린다.
 *  2-3 궁수의 공격 범위에 해당하는 칸을 확인한다. 이때 왼쪽>위>오른쪽 순서로 방문한다.
 *      보드 범위를 넘어가거나, 궁수와 같은 줄에 있는 칸은 확인하지 않는다.
 *  2-4 아직 죽지 않은 적 (이번에 다른 궁수에 의해 공격 당할 적도 포함)이라면 공격한다.
 *  2-5 모든 궁수가 공격했거나 죽일 수 있는 적이 없다면 한칸 전진한다.   
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int rowSize; // 세로 크기
	static int colSize; // 가로 크기
	static int distance; // 공격 범위
	static int[][] map; // 적 위치 정보

	static int ans; // 최대로 죽일 수 있는 배치에서의 죽인 적 수

	static Deque<int[]> Q;
	static int[] dx = { 0, -1, 0 }; // 좌 상 우
	static int[] dy = { -1, 0, 1 }; // ***같은 거리의 적이면 왼쪽 적부터***
	static int[][] kill; // 적이 죽었는지 여부
	
	static final int X = 0;
	static final int Y = 1;
	static final int DIS = 2;
	static final int ARCHER = 3;

	// 입력
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		distance = Integer.parseInt(st.nextToken());

		map = new int[rowSize + 1][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}

	// 적을 최대한 많이 죽일 수 있는 경우를 찾는다.
	static void solve() {
		ans = 0;
		
		// 궁수 배치 (조합)
		for (int left = 0; left < colSize - 2; left++) {
			for (int middle = left + 1; middle < colSize - 1; middle++) {
				for (int right = middle + 1; right < colSize; right++) {
					bfs(left, middle, right);
				}
			}
		}
	}

	// 현재 배치로 죽일 수 있는 적 개수 구하기
	static void bfs(int left, int middle, int right) {
		kill = new int[rowSize][colSize]; // 적이 죽었는지 여부

		// 적은 한칸씩 내려옴 (궁수를 한칸씩 올린다)
		for (int t = 0; t < rowSize; t++) {
			boolean[] attack = new boolean[3]; // 해당 초에서 궁수가 공격했는지 여부
			// 궁수 배치
			Q = new ArrayDeque<>();
			Q.offer(new int[] { rowSize - t, left, 0, 0 });
			Q.offer(new int[] { rowSize - t, middle, 0, 1 });
			Q.offer(new int[] { rowSize - t, right, 0, 2 });
			// 공격 가능한 범위를 확인하면서 공격할 적을 찾는다.
			while (!Q.isEmpty()) {
				// 모두 공격 완료
				if (attack[0] == true && attack[1] == true && attack[2] == true) {
					break;
				}
				int[] cur = Q.poll();
				// 공격범위 넘어감 || 이미 공격함
				if (cur[DIS] >= distance || attack[cur[ARCHER]]) {
					continue;
				}

				// 다음 칸 확인 (좌 > 상 > 우)
				for (int dir = 0; dir < 3; dir++) {
					int nx = cur[X] + dx[dir];
					int ny = cur[Y] + dy[dir];
					
					// 보드 범위를 넘거나, 궁수와 같은 줄에 도착하면 확인하지 않음
					if (nx < 0 || nx >= rowSize - t || ny < 0 || ny >= colSize) {
						continue; 
					}
					
					// 아직 죽지 않은 적인 경우 || 다른 궁수와 같이 공격할 적
					if (map[nx][ny] == 1 && (kill[nx][ny] == 0 || kill[nx][ny] == t + 1)) {
						kill[nx][ny] = t + 1;
						attack[cur[ARCHER]] = true;
						break; // 해당 궁수는 공격 종료
					}
					// 계속 진행
					Q.offer(new int[] { nx, ny, cur[DIS] + 1, cur[ARCHER] });
				}
			}
		}

		// 공격을 마친 후 죽인 적 세기
		int sum = 0;
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (kill[row][col] != 0) { // 0이면 적이 아니거나 죽지 않은 적
					sum++;
				}
			}
		}
		// 최대값 비교/갱신
		ans = Math.max(ans, sum);
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(ans);
	}

}