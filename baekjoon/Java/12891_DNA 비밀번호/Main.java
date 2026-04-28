/*
1. 입력
1-1 원본 DNA 문자열 길이
1-2 부분 DNA 문자열 길이
1-3 원본 DNA 문자열
1-4 A, C, G, T의 최소 포함 횟수

2. 주어진 길이(P)의 부분 문자열을 만든다.
2-1 1부터~P까지 각각의 문자의 등장횟수를 구한다.
2-2 하나씩 옆으로 이동하면서 횟수를 조정
2-3 암호로 사용 가능하면 횟수 추가

3. 출력
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int originLen;
	static int subLen;
	static String originStr;
	static int[] subStrCnt; // 최소로 포함할 문자 수
	static int[] strCnt; // 해당 부분 문자열의 문자 수

	static int ans; // 만들 수 있는 부분 문자열 개수

	static void solve() {
		// 0번째 문자부터 subLen-1 까지 A C G T 문자 횟수 구하기
		strCnt = new int[4];
		int leftIdx = 0;
		int rightIdx = subLen - 1;
		for (int idx = leftIdx; idx < rightIdx; idx++) {
			char character = originStr.charAt(idx);
			strCnt[checkACGT(character)]++;
		}

		ans = 0;
		for (; rightIdx < originLen; rightIdx++) {
			strCnt[checkACGT(originStr.charAt(rightIdx))]++;
			if (subStrCnt[0] <= strCnt[0] && subStrCnt[1] <= strCnt[1] && subStrCnt[2] <= strCnt[2]
					&& subStrCnt[3] <= strCnt[3]) {
				ans++;
			}
			strCnt[checkACGT(originStr.charAt(leftIdx++))]--;
		}

		// 출력
		System.out.println(ans);
	}

	// 입력
	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		originLen = Integer.parseInt(st.nextToken()); // 원본 문자열 길이
		subLen = Integer.parseInt(st.nextToken());

		// 원본 문자열
		originStr = br.readLine().trim();

		// 부분 문자열에 최소로 포함해야하는 A C G T 개수 입력
		subStrCnt = new int[4];
		st = new StringTokenizer(br.readLine().trim());
		subStrCnt[0] = Integer.parseInt(st.nextToken());
		subStrCnt[1] = Integer.parseInt(st.nextToken());
		subStrCnt[2] = Integer.parseInt(st.nextToken());
		subStrCnt[3] = Integer.parseInt(st.nextToken());
	}

	static int checkACGT(char character) {
		if (character == 'A') {
			return 0;
		} else if (character == 'C') {
			return 1;
		} else if (character == 'G') {
			return 2;
		} else { // T
			return 3;
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
	}

}
