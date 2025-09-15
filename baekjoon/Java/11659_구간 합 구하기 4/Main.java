/*
1. 입력
1-1 입력 받을 숫자 개수
1-2 테스트 횟수
1-3 숫자 목록
숫자 목록을 입력을 받을 때, 첫번째 숫자부터 현재 숫자까지의 합을 저장한다
numbers배열에서 이전 숫자까지의 합을 찾고 이번에 입력 받은 숫자를 더해서 numbers에 저장
배열을 만들 때 numbers 크기는 +1 해준다. -> 숫자에 대한 인덱스를 1번부터 사용
	-> 각 테스트마다 인덱스는 0~N-1이 아닌 1~N 으로 입력됨 (numbers[idx]로 접근)
	-> 범위 시작 숫자가 첫번째 숫자일때 numbers[-1]을 따로 처리하지 않아도 됨

2. 테스트
2-1 테스트 횟수만큼 범위 입력 받음
2-2 범위에 해당하는 숫자들의 합 구하기
	A, B 숫자가 입력되면 (A번째 숫자 포함)~(B번째 숫자 포함) 구간의 합은
	B번째 숫자까지의 누적합 - (A-1)번째 숫자까지의 누적합이다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int numberCnt;
	static int testCnt;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		// 입력을 받으면서 누적합 계산
		input();
		
		// 각 범위별 합 계산
		solve();
	}

	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		numberCnt = Integer.parseInt(st.nextToken());	// 숫자 개수 입력
		testCnt = Integer.parseInt(st.nextToken());	// 테스트 횟수 입력

		// 0번 인덱스를 따로 둬서 1번째 숫자에 대한 계산을 편리하게 하고,
		// 각 테스트마다 입력되는 인덱스를 그대로 사용할 수 있음
		numbers = new int[numberCnt + 1];	// 누적합을 저장할 배열 생성


		// 숫자를 입력 받으면서 누적합 계산
		// 누적합 : 이전 숫자들의 합 + 현재 입력 받은 숫자
		// 이전 숫자들의 합은 numbers[idx-1]로 바로 확인할 수 있다.
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 1; idx <= numberCnt; idx++) {
			int num = Integer.parseInt(st.nextToken());
			numbers[idx] = numbers[idx - 1] + num;
		}
	}
	
	// 각 테스트에서 주어지는 범위의 합 계산
	// 오른쪽 범위 - (왼쪽-1) 범위
	static void solve() throws IOException {
		for (int cnt = 0; cnt < testCnt; cnt++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			int left = Integer.parseInt(st.nextToken());	// 왼쪽 범위
			int right = Integer.parseInt(st.nextToken());	// 오른쪽 범위
			System.out.println(numbers[right] - numbers[left-1]);	// 구간합 계산, 출력
		}
	}

}