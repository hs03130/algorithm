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

	static int nodeCnt, edgeCnt, targetCnt;
	static ArrayList<int[]>[] adjList;
	static boolean[] visited;
	static long[] dist;
	static PriorityQueue<Edge> pq;
	static int ansNode = 0;
	static long ansWeight = 0L;

	static final int NODE = 0, WEIGHT = 1;

	static class Edge {
		int node;
		long weight; // 면접장까지의 거리

		public Edge(int node, long weight) {
			this.node = node;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());
		targetCnt = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[nodeCnt + 1];
		visited = new boolean[nodeCnt + 1];
		dist = new long[nodeCnt + 1];
		Arrays.fill(dist, Long.MAX_VALUE);

		// 간선 입력
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			if (adjList[to] == null) adjList[to] = new ArrayList<>();
			adjList[to].add(new int[] { from, weight });
		}

		// pq에 도시 번호와 도시에서 면접장까지의 거리를 저장한 Edge를 관리
		pq = new PriorityQueue<>((o1, o2) -> {
			// 거리가 가까운 것부터, 거리가 같으면 번호가 큰 것부터
			if (o1.weight == o2.weight)
				return Long.compare(o2.node, o1.node);
			return Long.compare(o1.weight, o2.weight);
		});

		// 면접장 입력
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < targetCnt; idx++) {
			int target = Integer.parseInt(st.nextToken());
			pq.add(new Edge(target, 0));
			dist[target] = 0;
		}

		// 면접장과 가까운 도시부터 확인
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (visited[cur.node]) continue;

			visited[cur.node] = true;
			// 마지막에 방문한 도시가 정답
			ansNode = cur.node;
			ansWeight = cur.weight;

			if (adjList[cur.node] == null) continue;
			for (int[] next : adjList[cur.node]) {
				if (visited[next[NODE]]) continue;
				if (dist[next[NODE]] < cur.weight + next[WEIGHT]) continue;
				
				dist[next[NODE]] = cur.weight + next[WEIGHT];
				pq.offer(new Edge(next[NODE], dist[next[NODE]]));
			}
		}

		bw.write(ansNode + "\n" + ansWeight);
		bw.flush();
		bw.close();
	}

}
