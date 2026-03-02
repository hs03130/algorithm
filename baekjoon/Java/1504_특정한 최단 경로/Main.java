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

	static int nodeCnt, edgeCnt, start = 1, target, stopover1, stopover2;
	static ArrayList<int[]>[] adjList;
	static int ans = 0;

	static final int COST = 0, NODE = 1, INF = Integer.MAX_VALUE / 3;

	static int solve(int start, int end) {
		// 최단 거리 초기화
		int[] dist = new int[nodeCnt + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;

		// 우선순위 큐에 시작점 추가
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[COST], o2[COST]));
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

		return dist[end];
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());
		target = nodeCnt;

		// 간선 입력
		adjList = new ArrayList[nodeCnt + 1];
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			if (adjList[from] == null) adjList[from] = new ArrayList<>();
			if (adjList[to] == null) adjList[to] = new ArrayList<>();
			adjList[from].add(new int[] { cost, to });
			adjList[to].add(new int[] { cost, from });
		}

		// 반드시 거쳐 갈 지점
		st = new StringTokenizer(br.readLine());
		stopover1 = Integer.parseInt(st.nextToken());
		stopover2 = Integer.parseInt(st.nextToken());

		ans = Math.min(solve(start, stopover2) + solve(stopover2, stopover1) + solve(stopover1, target),
				solve(start, stopover1) + solve(stopover1, stopover2) + solve(stopover2, target));

		if (ans >= INF) ans = -1;
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
