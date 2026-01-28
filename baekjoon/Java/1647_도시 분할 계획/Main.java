import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int nodeCnt, edgeCnt, maxCost, visitedCnt = 0;
	static ArrayList<int[]>[] adjList;
	static boolean[] visited;
	static PriorityQueue<int[]> pq;
	static long ans = 0L;

	static final int COST = 0, NODE = 1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[nodeCnt + 1];
		visited = new boolean[nodeCnt + 1];

		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if (adjList[u] == null) adjList[u] = new ArrayList<>();
			if (adjList[v] == null) adjList[v] = new ArrayList<>();
			adjList[u].add(new int[] { cost, v });
			adjList[v].add(new int[] { cost, u });
		}

		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[COST], o2[COST]));
		pq.offer(new int[] { 0, 1 });

		while (visitedCnt < nodeCnt) {
			int[] cur = pq.poll();
			if (visited[cur[NODE]]) continue;

			visited[cur[NODE]] = true;
			visitedCnt++;
			ans += cur[COST];
			maxCost = Math.max(maxCost, cur[COST]);

			for (int[] next : adjList[cur[NODE]]) {
				if (visited[next[NODE]]) continue;
				pq.add(next);
			}
		}
		
		ans -= maxCost;

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
