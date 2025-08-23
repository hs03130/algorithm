import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static final int MAX_V = 100_001;

	static int N, K;
	static int[] dist;
	static Deque<Integer> Q;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dist = new int[MAX_V];
		Arrays.fill(dist, -1);
		
		Q = new ArrayDeque<>();
		Q.offer(N);
		dist[N] = 0;

		while (!Q.isEmpty()) {
			int cur = Q.poll();

			if (cur == K)
				break;

			for (int next : new int[] { cur - 1, cur + 1, 2 * cur }) {
				if (next >= 0 && next < MAX_V && dist[next] == -1) {
					dist[next] = dist[cur] + 1;
					Q.offer(next);
				}
			}
		}

		System.out.println(dist[K]);

	}

}
