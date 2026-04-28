/*
 * 5kg 봉지수 + 3kg 봉지수 최소로
 * 5kg 최대로 사용(설탕/5 봉지) ~> 5kg 최소로 사용 (0 봉지)
 * 5kg로 넣고 남은 양을 3kg 채운다
 * 3kg 딱 맞게 채워지면 그게 최소 봉지수
 * 3kg만으로도 채우지 못하면 딱맞게 채울 수 없는 것
 * 
 * 1. 설탕 총량 입력
 * 2. 5kg 채울 수 있는 최대 양 ~ 최소 양 (0봉지) 쓰기
 *  2-1 남은 양을 3kg로 채우기
 *  2-2 딱 맞췄으면 5kg봉지수 + 3kg 봉지수 반환
 * 3. 딱 맞출수없음 -> -1 반환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int sugar;
	static int ans;

	// 입력
	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		sugar = Integer.parseInt(st.nextToken());
	}

	static void solve() {
		int maxCnt5 = sugar / 5;	// 5kg로 채울 수 있는 최대 봉지 수

		// 5kg 최대 ~ 최소 (0봉지) 사용
		for (int cnt5 = maxCnt5; cnt5 >= 0; cnt5--) {
			// 남은 양은 3kg로 채우기
			int cnt3 = (sugar - (cnt5 * 5)) / 3;
			// 딱 맞춰서 채움
			if (cnt5 * 5 + cnt3 * 3 == sugar) {
				ans = cnt5 + cnt3;
				return;
			}
		}
		// 딱 맞춰서 못 채움
		ans = -1;
		return;
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(ans);
	}

}