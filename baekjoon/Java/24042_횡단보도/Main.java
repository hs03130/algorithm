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

	static int nodeCnt, edgeCnt;
	static long[] dist;
	static ArrayList<int[]>[] adjList;
	static PriorityQueue<Edge> pq;

	static final int TIME = 0, NODE = 1, START = 1;

	static class Edge {
		int node;
		long time;

		public Edge(int node, long time) {
			this.node = node;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		// 최단 거리 초기화
		dist = new long[nodeCnt + 1];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[START] = 0;

		// 횡단보도에 불이 들어오는 순서
		adjList = new ArrayList[nodeCnt + 1];
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			if (adjList[u] == null) adjList[u] = new ArrayList<>();
			if (adjList[v] == null) adjList[v] = new ArrayList<>();
			adjList[u].add(new int[] { idx, v });
			adjList[v].add(new int[] { idx, u });
		}

		// 우선순위 큐에 시작점 추가
		pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.time, o2.time));
		pq.offer(new Edge(START, 0));

		// 다익스트라
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (dist[cur.node] != cur.time) continue;

			if (adjList[cur.node] == null) continue;
			for (int[] next : adjList[cur.node]) {
				// 현재 시간 이후에 켜지는 가장 빠른 신호 시간 계산
				// signStartTime + k*edgeCnt >= cur.time을 만족하는 최소 k
				// k = ceil((cur.time - signStartTime) / edgeCnt)
				long signStartTime = next[TIME];
				if (signStartTime < cur.time)
					signStartTime += ((cur.time - signStartTime + edgeCnt - 1) / edgeCnt) * edgeCnt;

				// 더 빠른 경로가 존재
				if (dist[next[NODE]] <= signStartTime + 1) continue;

				// 최단 거리 갱신
				dist[next[NODE]] = signStartTime + 1;
				pq.offer(new Edge(next[NODE], dist[next[NODE]]));
			}
		}

		bw.write(String.valueOf(dist[nodeCnt]));
		bw.flush();
		bw.close();
	}

}
