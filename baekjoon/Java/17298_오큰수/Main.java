import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[] nums;
	static int[] ans;
	static Deque<Integer> Q;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		ans = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(st.nextToken());
		}

		Q = new ArrayDeque<>();

		for (int idx = N - 1; idx >= 0; idx--) {
			while (!Q.isEmpty() && Q.peek() <= nums[idx]) {
				Q.pop();
			}

			if (Q.isEmpty()) {
				ans[idx] = -1;
			} else {
				ans[idx] = Q.peek();
			}

			Q.push(nums[idx]);
		}

		StringBuilder sb = new StringBuilder();
		for (int idx = 0; idx < N; idx++) {
			sb.append(ans[idx] + " ");
		}

		System.out.println(sb);
	}

}
