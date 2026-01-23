import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int nodeCnt, visitedCnt = 0;
	static int[][] adj;
	static boolean[] visited;
	static PriorityQueue<int[]> pq;
	static int ans = 0;

	static final int COST = 0, NODE = 1;

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());

		adj = new int[nodeCnt + 1][nodeCnt + 1];
		visited = new boolean[nodeCnt + 1];
		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[COST], o2[COST]));

		for (int node = 1; node <= nodeCnt; node++) {
			adj[node][node] = Integer.parseInt(br.readLine());
			pq.add(new int[] { adj[node][node], node });
		}

		for (int row = 1; row <= nodeCnt; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col <= nodeCnt; col++) {
				int cost = Integer.parseInt(st.nextToken());
				if (row == col) continue;
				adj[row][col] = cost;
			}
		}

		while (visitedCnt < nodeCnt) {
			int[] cur = pq.poll();
			if (visited[cur[NODE]]) continue;

			visited[cur[NODE]] = true;
			visitedCnt++;
			ans += cur[COST];

			for (int next = 1; next <= nodeCnt; next++) {
				if (!visited[next]) {
					pq.add(new int[] { adj[cur[NODE]][next], next });
				}
			}
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
