/*
 * 이분탐색 lower bound
 * 1. 좌표 개수 입력
 * 2. 원본 배열 입력, 임시 배열 입력
 * 3. 임시 배열 정렬
 * 4. 임시 배열에서 중복을 제거해서 리스트에 복사
 * 5. 원본 배열을 차례대로 확인하여 자신보다 작은 좌표 개수 찾기
 *    복사한 리스트로 이분 탐색 (lower bound)
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int elementCnt;
	static int[] originArr; // 원본 배열
	static int[] tmpArr; // 정렬된 originArr
	static List<Integer> bsList; // 이분 탐색을 할 중복이 제거된 tmpArr
	static StringBuilder ans;

	public static void main(String[] args) throws Exception {
		// 입력
		elementCnt = Integer.parseInt(br.readLine());

		originArr = new int[elementCnt];
		tmpArr = new int[elementCnt];

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < elementCnt; idx++) {
			originArr[idx] = Integer.parseInt(st.nextToken());
			tmpArr[idx] = originArr[idx];
		}

		// 풀이
		ans = new StringBuilder();

		// 원본 배열이 정렬된 임시 배열
		Arrays.sort(tmpArr);

		// 정렬된 임시 배열에서 중복 제거
		bsList = new ArrayList<>();
		int elementIdx = 0;
		bsList.add(tmpArr[0]);
		for (int idx = 0; idx < elementCnt; idx++) {
			if (bsList.get(elementIdx) != tmpArr[idx]) {
				bsList.add(tmpArr[idx]);
				elementIdx++;
			}
		}

		// 이분 탐색 (lower bound)
		for (int idx = 0; idx < elementCnt; idx++) {
			ans.append(Collections.binarySearch(bsList, originArr[idx]) + " ");
		}

		// 출력
		System.out.println(ans.toString());
	}

}