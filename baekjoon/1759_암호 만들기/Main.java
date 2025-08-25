/*
 * 1. 입력
 *  1-1 암호길이 | 입력될 문자 개수
 *  1-2 문자
 * 2. 문자 조합해서 암호 만들기
 * 3. 모음/자음 개수 세서 가능한 암호인지 확인 -> 가능하면 출력
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb;

	static int selectCnt;
	static int elementCnt;
	static int[] selectList;
	static char[] elementList;

	// 입력
	static void input() throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		selectCnt = Integer.parseInt(st.nextToken());
		elementCnt = Integer.parseInt(st.nextToken());

		selectList = new int[selectCnt];
		elementList = new char[elementCnt];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < elementCnt; idx++) {
			elementList[idx] = st.nextToken().charAt(0);
		}
	}

	static void solve() {
		sb = new StringBuilder();
		Arrays.sort(elementList);
		// 문자로 암호 조합
		bt(0);
	}

	static void bt(int selectIdx) {
		// 암호 완성
		if (selectIdx == selectCnt) {
			int vowelCnt = 0; // 모음 개수
			int consoCnt = 0; // 자음 개수
			// 완성된 암호를 돌면서 각각 개수 세기
			for (int idx = 0; idx < selectCnt; idx++) {
				if (checkVowel(elementList[selectList[idx]])) {
					vowelCnt++;
				} else {
					consoCnt++;
				}
			}
			// 모음|자음 개수 부족하면 불가능한 암호
			if (vowelCnt < 1 || consoCnt < 2) {
				return;
			}
			// 가능한 암호 : 출력
			for (int idx = 0; idx < selectCnt; idx++) {
				sb.append(elementList[selectList[idx]]);
			}
			sb.append("\n");
			return;
		}

		for (int idx = selectIdx == 0 ? 0 : selectList[selectIdx - 1] + 1; idx < elementCnt; idx++) {
			selectList[selectIdx] = idx;
			bt(selectIdx + 1);
		}
	}

	// 모음이면 true 자음이면 false 반환
	static boolean checkVowel(char ch) {
		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		input();
		solve();
		System.out.println(sb.toString());
	}

}