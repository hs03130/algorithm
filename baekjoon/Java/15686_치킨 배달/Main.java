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

	static int boardSize;
	static int[][] board;
	static List<int[]> homeList;
	static List<int[]> shopList;
	static int[][] distance;
	static int homeCnt;
	static int shopCnt;
	static int selectCnt;
	static int[] selectList;
	static int ans;

	static int HOME = 1;
	static int SHOP = 2;

	// 입력
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		boardSize = Integer.parseInt(st.nextToken());
		selectCnt = Integer.parseInt(st.nextToken());
		board = new int[boardSize][boardSize];
		homeList = new ArrayList<>();
		shopList = new ArrayList<>();
		for (int row = 0; row < boardSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				if (board[row][col] == HOME) {
					homeList.add(new int[] { row, col });
				} else if (board[row][col] == SHOP) {
					shopList.add(new int[] { row, col });
				}
			}
		}
	}

	static void solve() {
		homeCnt = homeList.size();
		shopCnt = shopList.size();
		selectList = new int[shopCnt];
		ans = Integer.MAX_VALUE;

		// 가게와 집 거리 구하기
		distance = new int[shopCnt][homeCnt];
		for (int shop = 0; shop < shopCnt; shop++) {
			for (int home = 0; home < homeCnt; home++) {
				distance[shop][home] = Math.abs(shopList.get(shop)[0] - homeList.get(home)[0])
						+ Math.abs(shopList.get(shop)[1] - homeList.get(home)[1]);
			}
		}

		// elementCnt개 중 selectCnt개의 치킨 가게 고르기
		func(0);
	}

	static void func(int selectIdx) {
		// 가게 모두 선택
		if (selectIdx == selectCnt) {
			// 선택된 가게중 최소 거리 더하기
			int sum = 0;
			int[] minDistance = new int[homeCnt];
			Arrays.fill(minDistance, Integer.MAX_VALUE);
			for (int home = 0; home < homeCnt; home++) {
				for (int shop = 0; shop < selectCnt; shop++) {
					if (distance[selectList[shop]][home] < minDistance[home]) {
						minDistance[home] = distance[selectList[shop]][home];
					}
				}
				sum += minDistance[home];
			}
			ans = Math.min(ans, sum);
			return;
		}

		// 가게 선택
		for (int idx = selectIdx == 0 ? 0 : selectList[selectIdx - 1] + 1; idx < shopCnt; idx++) {
			selectList[selectIdx] = idx;
			func(selectIdx + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(ans);
	}

}