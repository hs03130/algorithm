/*
 * M*N의 지도가 있다.
 * 얼어붙은 영역을 알려주면
 * 얼어붙은 영역을 제외하고 나머지 부분의 개수와 각각의 넓이를 입력한다.
 * 
 * 1. 입력
 *  1-1 행 크기 | 열 크기 | 영역 개수
 *  1-1 영역 좌표 (왼쪽아래 x,y | 오른쪽 위 x,y)
 *  
 * 2. 섬 영역 구하기
 *  2-1 입력을 받으면서 지도에 얼음 영역을 표시한다.
 *  2-2 지도 영역을 반복하면서 얼지 않은 부분이 나오면
 *      그 부분을 시작점으로 영역을 구한다. (BFS)
 *  2-3 개수증가, 넓이 저장
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int rowSize;
	static int colSize;
	static int iceCnt;
	static int[][] map;

	static int cnt;
	static List<Integer> sizeList;

	static Deque<int[]> Q;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int X = 0;
	static int Y = 1;

	// 입력
	static void input() throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		iceCnt = Integer.parseInt(st.nextToken());

		// 얼음 영역 표시
		map = new int[rowSize][colSize];
		for (int ice = 0; ice < iceCnt; ice++) {
			st = new StringTokenizer(br.readLine().trim());
			int leftY = Integer.parseInt(st.nextToken());
			int leftX = Integer.parseInt(st.nextToken());
			int rightY = Integer.parseInt(st.nextToken());
			int rightX = Integer.parseInt(st.nextToken());
			for (int row = leftX; row < rightX; row++) {
				for (int col = leftY; col < rightY; col++) {
					map[row][col] = 1;
				}
			}
		}
	}

	// 섬 개수, 넓이 구하기
	static void solve() {
		cnt = 0;
		sizeList = new ArrayList<>(); // 오름차순
		Q = new ArrayDeque<>();

		// 지도를 순회하면서 얼음이 아닌 시작점 찾기
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (map[row][col] == 0) {
					// 시작점
					int size = 0; // 넓이
					
					Q.offer(new int[] { row, col });
					map[row][col] = 1;
					while (!Q.isEmpty()) {
						int[] cur = Q.poll();
						size++;
						for (int dir = 0; dir < 4; dir++) {
							int nx = cur[X] + dx[dir];
							int ny = cur[Y] + dy[dir];
							// 지도 영역 내에서만
							if (nx < 0 || nx >= rowSize || ny < 0 || ny >= colSize) {
								continue;
							}
							// 얼음이거나 이미 확인한 섬 칸
							if (map[nx][ny] == 1) {
								continue;
							}
							// 섬 영역 넓이 증가
							map[nx][ny] = 1;
							Q.offer(new int[] { nx, ny });
						}
					}
					cnt++; // 섬 영역 개수 증가
					sizeList.add(size); // 넓이 추가
				}
			}
		}
		// 넓이 오름차순으로
		Collections.sort(sizeList);
	}

	public static void main(String[] args) throws Exception {
		input();
		solve();

		// 섬 개수
		System.out.println(cnt);
		// 섬 영역별 넓이 (오름차순)
		for (int size : sizeList) {
			System.out.print(size + " ");
		}
	}
}
