/*
1. 입력
1-1 배열 크기, 테스트 횟수
1-2 숫자 목록
	숫자 목록을 입력을 받을 때, 해당 칸의 인덱스가 r,c 라면 (1~r x 1~c)의 숫자들을 누적해서 저장
	누적합(r,c) = 누적합(r-1,c) + 누적합(r,c-1) - 누적합(r-1,c-1) + [r][c]칸 숫자
        예를들어 
	1 2 3 
	2 3 4 라고 하면
	
	1 3 6
	3 8 15 으로 누적해서 저장한다.
	
	이때, 배열크기는 +1 해서 생성한다. -> 인덱스를 1번부터 사용
	-> 각 테스트마다 인덱스는 0~N-1이 아닌 1~N 으로 입력됨 (numbers[idx][]로 접근)
	-> 인덱스가 1일때 numbers[-1]을 따로 처리하지 않아도 됨

2. 테스트
2-1 테스트 횟수만큼 범위 입력 받음 (x1, y1), (x2, y2)
2-2 범위에 해당하는 숫자들의 합 구하기
	: 누적합(x2, y2) - 누적합(x1-1, y2) - 누적합(x2, y1-1) + 누적합(x1-1, y1-1) 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int boardSize;
	static int testCnt;
	static int[][] numbers;

	public static void main(String[] args) throws IOException {
		// 입력을 받으면서 누적합 계산
		input();

		// 각 범위별 합 계산
		solve();
	}
	
	// 입력, 누적합 계산
	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		boardSize = Integer.parseInt(st.nextToken()); // 배열 크기 입력
		testCnt = Integer.parseInt(st.nextToken()); // 테스트 횟수 입력
		
		// 0번 인덱스를 따로 둬서 1번째 숫자에 대한 계산을 편리하게 하고,
		// 각 테스트마다 입력되는 인덱스를 그대로 사용할 수 있음
		numbers = new int[boardSize + 1][boardSize + 1]; // 누적합을 저장할 배열 생성

		// 숫자를 입력 받으면서 누적합 계산
		for (int row = 1; row <= boardSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 1; col <= boardSize; col++) {
				int num = Integer.parseInt(st.nextToken());
				numbers[row][col] = numbers[row][col - 1] + numbers[row - 1][col] - numbers[row - 1][col - 1] + num;
			}
		}
		
	}

	// 각 테스트에서 주어지는 범위의 합 계산
	static void solve() throws IOException {
		// 테스트 횟수만큼 반복
		for (int cnt = 0; cnt < testCnt; cnt++) {
			// 구간합을 구할 범위 좌표
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			int[] left = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) }; // 왼쪽 범위
			int[] right = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) }; // 오른쪽 범위
			
			// 구간합 계산, 출력
			System.out.println(numbers[right[0]][right[1]] - numbers[right[0]][left[1]-1] - numbers[left[0]-1][right[1]] + numbers[left[0]-1][left[1]-1]);
		}
	}

}