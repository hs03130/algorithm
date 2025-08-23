/*
 * 전체 치킨 가게 중 일부의 치킨 가게를 조합하여
 * 각 집과 치킨 가게를 연결하는 최소 값의 합이 최소인 경우를 구한다.
 * 
 * 1. 입력
 *  1-1 보드 크기 | 선택할 치킨 가게 개수
 *  1-2 보드 정보 (치킨 : 2 , 집 : 1)
 * 2. 최소 거리 가게 선택하기
 *  2-1 집-가게 거리 구하기
 *  2-2 가게 선택하기
 *  2-3 집-(선택된)가게 거리 최소합 구하기 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int rowSize;
	static int colSize;
	static int[][] board;
	static List<int[]> elementList;
	static int blindSpotCnt;

	static int elementCnt;
	static int[] selectList;
	static int ans;
	
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int X = 0;
	static int Y = 1;
	static int TYPE = 2;

	static int[][][] directionType = { 
			{ { 0 }, { 1 }, { 2 }, { 3 } }, 
			{ { 0, 2 }, { 1, 3 } },
			{ { 0, 1 }, { 1, 2 }, { 2, 3 }, { 0, 3 } }, 
			{ { 0, 1, 2 }, { 1, 2, 3 }, { 2, 3, 0 }, { 3, 0, 1 } },
			{ { 0, 1, 2, 3 } } };

	// 입력
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		board = new int[rowSize][colSize];
		elementList = new ArrayList<>();
		blindSpotCnt = 0;
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				if (board[row][col] > 0 && board[row][col] < 6) {
					elementList.add(new int[] { row, col, board[row][col]-1 });
				} else if (board[row][col] == 0) {
					blindSpotCnt++;
				}
			}
		}
	}

	static void solve() {
		ans = 0;
		elementCnt = elementList.size();
		selectList = new int[elementCnt];
		func(0);
	}
	
	static void func(int elementIdx) {
		if (elementIdx == elementCnt) {
			calculate();
			return;
		}
		
		int[] element = elementList.get(elementIdx);
		// CCTV TYPE에 따라 가능한 방향 모두
		for(int direction=0; direction<directionType[element[TYPE]].length; direction++) {
			selectList[elementIdx] = direction;
			func(elementIdx + 1);
		}
	}
	
	static void calculate() {
		// 보드 복사
		int[][] copyBoard = new int[rowSize][colSize];
		for(int row=0; row<rowSize; row++) {
			copyBoard[row] = board[row].clone();
		}
		// 선택된 방향 따라 보드 채우기
		int len = 0;
		for(int idx=0; idx<elementCnt; idx++) {
			int[] cur = elementList.get(idx);
			for(int direction=0; direction<directionType[cur[TYPE]][selectList[idx]].length; direction++) {
				int dir = directionType[cur[TYPE]][selectList[idx]][direction];
				int nx = cur[X];
				int ny = cur[Y];
				while(true) {
					nx += dx[dir];
					ny += dy[dir];
					if (nx < 0 || nx >= rowSize || ny <0 || ny >=colSize) {
						break;
					}
					if (copyBoard[nx][ny] == 6) {
						break;
					}
					if (copyBoard[nx][ny] != 0) {
						continue;
					}
					copyBoard[nx][ny] = -1;
					len++;
				}
			}
		}
		ans = Math.max(ans, len);
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(blindSpotCnt-ans);
	}

}
