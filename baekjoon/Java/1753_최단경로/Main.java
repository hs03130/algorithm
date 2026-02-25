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

	static int nodeCnt, edgeCnt, start;
	static int[] dist;
	static ArrayList<int[]>[] adjList;
	static PriorityQueue<int[]> pq;

	static final int COST = 0, NODE = 1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());

		// 최단 거리 초기화
		dist = new int[nodeCnt + 1]; // start에서 해당 정점으로의 최단 거리
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		// 간선 입력
		adjList = new ArrayList[nodeCnt + 1];
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			if (adjList[from] == null) adjList[from] = new ArrayList<>();
			adjList[from].add(new int[] { cost, to });
		}

		// 우선순위 큐에 시작점 추가
		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[COST], o2[COST]));
		pq.offer(new int[] { 0, start });

		// 다익스트라
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();

			if (dist[cur[NODE]] != cur[COST]) continue;

			if (adjList[cur[NODE]] == null) continue;
			for (int[] next : adjList[cur[NODE]]) {
				if (dist[next[NODE]] <= dist[cur[NODE]] + next[COST]) continue;
				dist[next[NODE]] = dist[cur[NODE]] + next[COST];
				pq.offer(new int[] { dist[next[NODE]], next[NODE] });
			}
		}

		// start에서 해당 정점으로의 최단 거리 출력
		for (int idx = 1; idx <= nodeCnt; idx++) {
			if (dist[idx] == Integer.MAX_VALUE) sb.append("INF\n");
			else sb.append(dist[idx] + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
