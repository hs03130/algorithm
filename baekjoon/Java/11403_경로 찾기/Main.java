import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int V;
	static int[][] adj;
	static int[][] ans;
	static Deque<Integer> Q;

	public static void main(String[] args) throws IOException {

		V = Integer.parseInt(br.readLine());

		adj = new int[V][V];
		for (int from = 0; from < V; from++) {
			st = new StringTokenizer(br.readLine());
			for (int to = 0; to < V; to++) {
				adj[from][to] = Integer.parseInt(st.nextToken());
			}
		}

		ans = new int[V][V];
		for (int from = 0; from < V; from++) {
			Q = new ArrayDeque<>();
			for (int to = 0; to < V; to++) {
				if (adj[from][to] == 1) {
					ans[from][to] = 1;
					Q.offer(to);
				}

			}
			while (!Q.isEmpty()) {
				int cur = Q.poll();
				for (int to = 0; to < V; to++) {
					if (adj[cur][to] == 1 && ans[from][to] != 1) {
						ans[from][to] = 1;
						Q.offer(to);
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int[] v : ans) {
			for (int e : v) {
				sb.append(e + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
