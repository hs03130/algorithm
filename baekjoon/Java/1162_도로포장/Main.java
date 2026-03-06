import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt, edgeCnt, paveCnt;
	static long[][] dist;
	static ArrayList<Edge>[] adjList;
	static PriorityQueue<Edge> pq;
	static long ans = Long.MAX_VALUE;

	static final int COST = 0, PAVE_CNT = 1, NODE = 2, START = 1;

	static class Edge {
		int node, pave;
		long cost;

		public Edge(int node, int pave, long cost) {
			this.node = node;
			this.pave = pave;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());
		paveCnt = Integer.parseInt(st.nextToken());

		dist = new long[nodeCnt + 1][paveCnt + 1];
		adjList = new ArrayList[nodeCnt + 1];
		for (int idx = 1; idx <= nodeCnt; idx++) {
			Arrays.fill(dist[idx], Long.MAX_VALUE);
			adjList[idx] = new ArrayList<>();
		}
		dist[START][0] = 0;

		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			// 포장하지 않고 가는 간선
			adjList[u].add(new Edge(v, 0, cost));
			adjList[v].add(new Edge(u, 0, cost));
			// 포장하고 가는 간선
			adjList[u].add(new Edge(v, 1, 0));
			adjList[v].add(new Edge(u, 1, 0));
		}

		// 우선순위 큐에 시작점 추가
		pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.cost, o2.cost));
		pq.offer(new Edge(START, 0, 0));

		// 다익스트라
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (dist[cur.node][cur.pave] != cur.cost) continue;

			for (Edge next : adjList[cur.node]) {
				if (cur.pave + next.pave > paveCnt) continue;
				if (dist[next.node][cur.pave + next.pave] <= dist[cur.node][cur.pave] + next.cost) continue;
				dist[next.node][cur.pave + next.pave] = dist[cur.node][cur.pave] + next.cost;
				pq.offer(new Edge(next.node, cur.pave + next.pave, dist[next.node][cur.pave + next.pave]));
			}
		}

		for (int cnt = 0; cnt <= paveCnt; cnt++) {
			ans = Math.min(ans, dist[nodeCnt][cnt]);
		}
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
