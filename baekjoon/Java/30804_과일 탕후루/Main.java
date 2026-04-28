import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int elementCnt;
	static int[] elementList; // 탕후루
	static int[] selectList; // 남길 과일 두 종류
	static int[] lastIndex;

	static int ans;

	static final int FIRST = 0;
	static final int SECOND = 1;

	public static void main(String[] args) throws Exception {
		// 입력
		elementCnt = Integer.parseInt(br.readLine());

		elementList = new int[elementCnt];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < elementCnt; idx++) {
			elementList[idx] = Integer.parseInt(st.nextToken());
		}

		// 풀이
		ans = 0;
		selectList = new int[2];
		lastIndex = new int[10];

		// 가장 앞쪽 과일이 첫 번째 과일
		int left = 0;
		int right = 0;
		selectList[FIRST] = elementList[left];

		for (; right <= elementCnt; right++) {
			if (right == elementCnt) {
				ans = Math.max(ans, right - left);
				break;
			}

			// 두 번째 과일을 아직 고르지 않았음
			if (elementList[right] != selectList[FIRST] && selectList[SECOND] == 0) {
				selectList[SECOND] = elementList[right];
			}

			// 과일 종류 2개 초과
			if (elementList[right] != selectList[FIRST] && elementList[right] != selectList[SECOND]) {
				ans = Math.max(ans, right - left);

				left = elementList[right - 1] == selectList[SECOND] ? lastIndex[selectList[FIRST]] + 1 : lastIndex[selectList[SECOND]] + 1;
				selectList[FIRST] = elementList[left];
				selectList[SECOND] = elementList[right];
			}

			if (elementList[right] == selectList[FIRST]) {
				lastIndex[selectList[FIRST]] = right;
			} else {
				lastIndex[selectList[SECOND]] = right;
			}

		}

		// 출력
		System.out.println(ans);
	}

}