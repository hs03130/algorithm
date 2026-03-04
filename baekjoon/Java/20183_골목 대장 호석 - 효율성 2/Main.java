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

	static int nodeCnt, edgeCnt, start, end;
	static long money;
	static long[] dist;
	static ArrayList<int[]>[] adjList;
	static PriorityQueue<Edge> pq;
	static int ans;

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

	// maxCost원 이하의 간선만 사용해 목적지까지 이동 가능한지 여부
	static boolean solve(int maxCost) {
		// 최단 거리 초기화
		dist = new long[nodeCnt + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;

		// 우선순위 큐에 시작점 추가
		pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.cost, o2.cost));
		pq.offer(new Edge(start, 0L));

		// 다익스트라
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (dist[cur.node] != cur.cost) continue;

			if (adjList[cur.node] == null) continue;
			for (int[] next : adjList[cur.node]) {
				// 간선 비용이 maxCost 초과면 사용 불가
				if (next[COST] > maxCost) continue;
				
				// 소지금 부족
				if (dist[cur.node] + next[COST] > money) continue;

				// 이동 가능
				if (next[NODE] == end) return true;
				
				if (dist[next[NODE]] <= dist[cur.node] + next[COST]) continue;
				dist[next[NODE]] = dist[cur.node] + next[COST];
				pq.offer(new Edge(next[NODE], dist[next[NODE]]));
			}
		}

		// 이동 불가능
		return false;
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		money = Long.parseLong(st.nextToken());

		// 간선 입력
		adjList = new ArrayList[nodeCnt + 1];
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

		// 최대 수금액을 기준으로 이분 탐색
		// mid원 이하의 간선만 사용해 목적지까지 이동 가능하지 검사
		// 이동 가능한 mid의 최소값이 정답
		int left = 0, right = 1000000000;
		while (left + 1 < right) {
			int mid = (right + left) / 2;

			if (!solve(mid)) left = mid;
			else right = mid;
		}

		if (solve(right)) ans = right;
		else ans = -1;

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
