import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int testCase;

	static int N, findIndex, findNum;
	static Integer[] importance;
	static Deque<int[]> Q;
	static int ans;

	static final int NUM = 0;
	static final int INDEX = 1;

	public static void main(String[] args) throws IOException {

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			findIndex = Integer.parseInt(st.nextToken());

			importance = new Integer[N];
			Q = new ArrayDeque<>();

			st = new StringTokenizer(br.readLine());
			for (int idx = 0; idx < N; idx++) {
				importance[idx] = Integer.parseInt(st.nextToken());
				Q.offer(new int[] { importance[idx], idx });
			}
			findNum = importance[findIndex];

			// 중요도 내림차순 정렬
			Arrays.sort(importance, Collections.reverseOrder());

			ans = 0;
			for (int idx = 0; idx < N; idx++) {
				// 현재 우선 순위가 가장 높은 숫자가 나올 때까지 뒤로 이동
				while (Q.peek()[NUM] != importance[idx]) {
					Q.offer(Q.poll());
				}
				
				ans++;

				// 찾는 위치의 숫자가 나올 차례
				if (Q.poll()[INDEX] == findIndex) {
					System.out.println(ans);
					break;
				}
			}

		}

	}

}
