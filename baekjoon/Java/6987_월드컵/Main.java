/*
 * 6개의 팀이 서로 승부해서 경기 결과를 보여줌
 * 결과가 유효한지 판단하는 문제 (4번 반복)
 * 
 * 1. 입력
 *  1-1 경기 결과 입력
 *  1-2 팀 별 승부는 5번
 * 
 * 2. 경기 결과 만들기
 *  2-1 1팀을 기준으로 2~6팀과의 결과 고르기 (승/무/패)
 *      이 때 입력으로 주어진 각각의 승무패 수를 넘지 않는 선에서 고른다.
 *  2-2 1팀을 기준으로 모두 골랐다면 2팀을 기준으로 3~6팀과의 결과 고르기
 *  2-3 5팀까지 모두 골랐다면 6팀은 자동으로 완성된다.
 *  2-4 만들어낸 결과가 입력과 동일한지 확인  
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int TEAM_CNT = 6;
	static int PLAY_CNT = 5;

	static int[][] result; // 승패 결과
	static int[][] pm; // 조합한 결과
	static int ans; // 유효한지 아닌지

	// 입력
	static boolean input() throws IOException {
		st = new StringTokenizer(br.readLine());
		result = new int[TEAM_CNT][3];
		pm = new int[TEAM_CNT][3];

		ans = 0;
		// 1-1 나라마다 경기 결과 입력
		for (int row = 0; row < TEAM_CNT; row++) {
			// 승 무 패
			for (int col = 0; col < 3; col++) {
				result[row][col] = Integer.parseInt(st.nextToken());
			}
			// 1-2 승 무 패의 합이 5가 아니면 잘못된 결과이다.
			if (result[row][0] + result[row][1] + result[row][2] != 5) {
				return false;
			}

		}
		return true;
	}

	// 경기 결과 만들기
	static void solve() {
		// 1번째 팀부터 2~6팀에 대한 경기 결과를 선택하면서 모든 경기 결과를 만들어 본다.
		if (func(0, 1)) {
			ans = 1;
		} else {
			ans = 0;
		}
	}

	static boolean func(int curTeam, int oppTeam) {
		// 2-2 한 팀에 대해 모든 결과를 선택했음
		if (oppTeam == TEAM_CNT) {
			// 만들어낸 결과가 입력과 동일하지 않음
			if (pm[curTeam][0] != result[curTeam][0] || pm[curTeam][1] != result[curTeam][1]
					|| pm[curTeam][2] != result[curTeam][2]) {
				return false;
			}
			// 만들어낸 결과가 입력과 동일함 -> 다음 팀에 대해 결과 선택
			curTeam++; // 다음 팀
			oppTeam = curTeam + 1;
		}
		
		// 2-3 5팀까지 모두 골랐다면 마지막팀인 6팀은 자동으로 결정됨
		if (curTeam == TEAM_CNT -1) {
			return true;
		}

		// 2-1 curTeam에 대해 경기 결과 만들기
		for (int oppIdx = oppTeam; oppIdx < TEAM_CNT; oppIdx++) {
			// 승
			if (pm[curTeam][0] < result[curTeam][0] && pm[oppIdx][2] < result[oppIdx][2]) {
				pm[curTeam][0]++;
				pm[oppIdx][2]++;
				if (func(curTeam, oppIdx + 1)) {
					return true;
				}
				pm[curTeam][0]--;
				pm[oppIdx][2]--;
			}
			// 무
			if (pm[curTeam][1] < result[curTeam][1] && pm[oppIdx][1] < result[oppIdx][1]) {
				pm[curTeam][1]++;
				pm[oppIdx][1]++;
				if (func(curTeam, oppIdx + 1)) {
					return true;
				}
				pm[curTeam][1]--;
				pm[oppIdx][1]--;
			}

			// 패
			if (pm[curTeam][2] < result[curTeam][2] && pm[oppIdx][0] < result[oppIdx][0]) {
				pm[curTeam][2]++;
				pm[oppIdx][0]++;
				if (func(curTeam, oppIdx + 1)) {
					return true;
				}
				pm[curTeam][2]--;
				pm[oppIdx][0]--;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		for (int test_case = 1; test_case <= 4; test_case++) {
			if (input()) {
				solve();
			}
			System.out.print(ans + " ");
		}
	}

}