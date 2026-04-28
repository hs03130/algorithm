/*
1. 입력
1-1 재료 수 1<= cnt <= 10
1-2 재료마다 신맛 쓴맛

2. 1개 이상의 재료를 조합한다.
2-1 종료 조건 : 모든 재료에 대해 조합을 고려했을 때
	조합 한 재료의 신맛 합과 쓴맛 합의 차를 구한다.
	최소값보다 작으면 갱신
2-2 재료를 넣거나 빼서 계속 진행
	재료를 넣기 전에 taste 정보에 신맛 곱하고, 쓴맛 더해줌
	재료를 넣고 재귀 진행
	재귀가 끝나면 taste 정보에 신맛 나누고, 쓴맛 빼줌
	재귀 진행
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int ingredientCnt;
	static int[][] tasteList;
	static int ans;
	static int[] taste;

	// 넣을지 말지 확인한 재료수, 고른 재료수
	static void powerSet(int ingredientIdx, int selectIdx) {
		// 모든 재료에 대해 조합함
		if (ingredientIdx == ingredientCnt) {
			// 하나 이상 선택해야함
			if (selectIdx == 0) return;
			// 최소값이면 갱신
			ans = Math.abs(taste[0]-taste[1]) < ans ? Math.abs(taste[0]-taste[1]) : ans;
			return;
		}

		// 재료를 넣고 계속 조합
		taste[0] *= tasteList[ingredientIdx][0];	// 신맛은 곱
		taste[1] += tasteList[ingredientIdx][1];	// 쓴맛은 합
		powerSet(ingredientIdx + 1, selectIdx + 1);

		// 재료를 넣지 않고 계속 조합
		taste[0] /= tasteList[ingredientIdx][0];	// 신맛은 곱>나누기
		taste[1] -= tasteList[ingredientIdx][1];	// 쓴맛은 합>빼기
		powerSet(ingredientIdx + 1, selectIdx);
	}
	
	static void solve() {
		ans = Integer.MAX_VALUE;
		taste = new int[2];
		taste[0] = 1;	// 0에 곱하면 안되기 때문에 1로 꼭 초기화
		taste[1] = 0;
		// 재료 조합 구하기
		powerSet(0, 0);
		
		// 출력
		System.out.println(ans);
	}

	// 입력
	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		ingredientCnt = Integer.parseInt(st.nextToken());	// 재료수
		
		tasteList = new int[ingredientCnt][2];	// 맛 정보 담을 배열
		// 각 재료마다 맛 정보 받기
		for(int idx=0; idx<ingredientCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			tasteList[idx][0] = Integer.parseInt(st.nextToken());
			tasteList[idx][1] = Integer.parseInt(st.nextToken());
		}
	}
	
	public static void main(String[] args) throws IOException {
		input();
		solve();
	}

}
