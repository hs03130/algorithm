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
	static int[] height;
	static int[] ans;

	static Deque<Integer> Q;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());
		height = new int[N];
		ans = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			height[idx] = Integer.parseInt(st.nextToken());
		}

		Q = new ArrayDeque<>();

		for (int idx = 0; idx < N; idx++) {
			while (!Q.isEmpty() && height[Q.peek()] < height[idx]) {
				Q.pop();
			}

			if (Q.isEmpty()) {
				ans[idx] = 0;
			} else {
				ans[idx] = Q.peek() + 1;
			}
			
			Q.push(idx);
		}

		StringBuilder sb = new StringBuilder();
		for (int idx = 0; idx < N; idx++) {
			sb.append(ans[idx] + " ");
		}
		System.out.println(sb);
	}

}