import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt, edgeCnt, start, end;
	static int[] dist, prev;
	static ArrayList<int[]>[] adjList;
	static PriorityQueue<int[]> pq;

	static final int COST = 0, NODE = 1;

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());
		edgeCnt = Integer.parseInt(br.readLine());

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

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		// 최단 거리 초기화
		dist = new int[nodeCnt + 1]; // start에서 해당 정점으로의 최단 거리
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		prev = new int[nodeCnt + 1]; // 해당 정점 직전에 방문해야 하는 정점

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
				prev[next[NODE]] = cur[NODE];
				pq.offer(new int[] { dist[next[NODE]], next[NODE] });
			}
		}

		// 출력
		ArrayList<Integer> path = new ArrayList<>();
		for (int node = end; node != 0; node = prev[node]) {
			path.add(node);
		}
		Collections.reverse(path);

		sb.append(dist[end] + "\n"); // 최소 비용
		sb.append(path.size() + "\n"); // 경로에 포함된 도시 개수
		for (int node : path) sb.append(node + " "); // 도시 순서대로

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
