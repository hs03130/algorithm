import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int V, E;
	static List<Integer>[] adjList;

	static int minSum = Integer.MAX_VALUE;
	static int ans = -1;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[V + 1];
		for (int idx = 1; idx <= V; idx++) {
			adjList[idx] = new ArrayList<>();
		}

		for (int idx = 0; idx < E; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adjList[from].add(to);
			adjList[to].add(from);
		}

		for (int idx = 1; idx <= V; idx++) {
			int[] dist = new int[V + 1];
			Arrays.fill(dist, -1);
			Queue<Integer> Q = new ArrayDeque<>();

			Q.offer(idx);
			dist[idx] = 0;

			while (!Q.isEmpty()) {
				int cur = Q.poll();
				for (int next : adjList[cur]) {
					if (dist[next] == -1) {
						dist[next] = dist[cur] + 1;
						Q.offer(next);
					}
				}
			}

			int sum = 0;
			for (int v = 1; v <= V; v++) {
				sum += dist[v];
			}

			if (sum < minSum) {
				minSum = sum;
				ans = idx;
			}
		}

		System.out.println(ans);
	}

}
