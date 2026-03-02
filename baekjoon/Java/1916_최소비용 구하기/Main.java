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
	static int[] dist;
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

		// 시작점, 도착점 입력
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		// 최단 거리 초기화
		dist = new int[nodeCnt + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

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

		bw.write(String.valueOf(dist[end]));
		bw.flush();
		bw.close();
	}

}
