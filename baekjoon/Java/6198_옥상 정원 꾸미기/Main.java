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
	static long ans = 0L;

	static Deque<Integer> Q;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());
		height = new int[N];
		for (int idx = 0; idx < N; idx++) {
			height[idx] = Integer.parseInt(br.readLine());
		}

		Q = new ArrayDeque<>();
		for (int idx = N - 1; idx >= 0; idx--) {
			while (!Q.isEmpty() && height[Q.peek()] < height[idx]) {
				Q.pop();
			}

			if (Q.isEmpty()) {
				ans += N - idx - 1;
			} else {
				ans += Q.peek() - idx - 1;
			}
			Q.push(idx);
		}

		System.out.println(ans);
	}

}
