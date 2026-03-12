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
	static StringTokenizer st;

	static int nodeCnt, edgeCnt, start, end;
	static boolean[] isInaccessible;
	static long[] distStartToEnd, distEndToStart;
	static ArrayList<int[]>[] adjList;
	static PriorityQueue<Edge> pq;
	static long ans;

	static final int COST = 0, NODE = 1;
	static final long INF = Long.MAX_VALUE / 2;

	static class Edge {
		int node;
		long cost;

		public Edge(int node, long cost) {
			this.node = node;
			this.cost = cost;
		}
	}

	static void solve(int from, long[] dist) {
		dist[from] = 0;

		pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.cost, o2.cost));
		pq.offer(new Edge(from, 0L));

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (dist[cur.node] != cur.cost) continue;

			for (int[] next : adjList[cur.node]) {
				if (isInaccessible[next[NODE]]) continue;

				if (dist[next[NODE]] <= cur.cost + next[COST]) continue;
				dist[next[NODE]] = cur.cost + next[COST];
				pq.offer(new Edge(next[NODE], dist[next[NODE]]));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		// 초기화
		distStartToEnd = new long[nodeCnt + 1];
		distEndToStart = new long[nodeCnt + 1];
		adjList = new ArrayList[nodeCnt + 1];
		for (int idx = 1; idx <= nodeCnt; idx++) {
			adjList[idx] = new ArrayList<>();
			distStartToEnd[idx] = INF;
			distEndToStart[idx] = INF;
		}

		// 간선 입력
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adjList[u].add(new int[] { cost, v });
			adjList[v].add(new int[] { cost, u });
		}
		for (int idx = 1; idx <= nodeCnt; idx++) {
			adjList[idx].sort((o1, o2) -> Integer.compare(o1[NODE], o2[NODE]));
		}

		// 출발지, 도착지 입력
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		// 방문할 수 없는 정점인지. 처음에는 모두 가능
		isInaccessible = new boolean[nodeCnt + 1];

		solve(start, distStartToEnd);
		solve(end, distEndToStart);
		ans = distStartToEnd[end];
		
		int curNode = start;
		while (curNode != end) {
			for (int[] next : adjList[curNode]) {
				if (isInaccessible[next[NODE]]) continue;

				// 출발지 -> 현재 정점 -> 다음 정점 -> 도착지 최소 비용이 출발지 -> 도착지와 같으면 다음에 방문해야되는 정점이 맞다.
				// 각 간선은 정점 번호에 대하여 오름차순으로 정렬되어 있기 때문에 사전순이 보장됨.
				if (distStartToEnd[curNode] + next[COST] + distEndToStart[next[NODE]] == distStartToEnd[end]) {
					isInaccessible[next[NODE]] = true;
					curNode = next[NODE];
					break;
				}
			}
		}

		isInaccessible[start] = false;
		Arrays.fill(distEndToStart, INF);
		solve(end, distEndToStart);
		ans += distEndToStart[start];

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
