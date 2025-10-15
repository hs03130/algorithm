import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int treeCnt; // 나무 개수
	static int treeLen; // 잘라갈 나무 길이
	static int[] treeList; // 나무 높이 목록
	static int maxH; // 가장 큰 나무의 높이
	static int ans;

	public static void main(String[] args) throws Exception {
		// 입력
		st = new StringTokenizer(br.readLine());
		treeCnt = Integer.parseInt(st.nextToken());
		treeLen = Integer.parseInt(st.nextToken());

		treeList = new int[treeCnt];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < treeCnt; idx++) {
			treeList[idx] = Integer.parseInt(st.nextToken());
			maxH = Math.max(treeList[idx], maxH);
		}

		// 풀이
		ans = -1;

		// 절단기 높이는 0~maxH-1 사이로 설정할 수 있다.
		int left = 0;
		int right = maxH;

		while (left + 1 < right) {
			int mid = (right + left) / 2;

			// 가능한 높이
			if (check(mid)) {
				left = mid;
			}
			// 부족함
			else {
				right = mid;
			}
		}
		ans = left;
		
		// 출력
		System.out.println(ans);
	}

	static boolean check(int cutH) {
		long sum = 0;
		for(int idx=0; idx<treeCnt; idx++) {
			if (treeList[idx] > cutH) {
				sum += treeList[idx] - cutH;
			}
		}
		return sum >= treeLen;
	}
}