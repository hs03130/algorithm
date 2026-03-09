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
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt, edgeCnt, k;
	static PriorityQueue<Integer>[] dist;
	static ArrayList<int[]>[] adjList;
	static PriorityQueue<int[]> pq;

	static final int COST = 0, NODE = 1, START = 1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		dist = new PriorityQueue[nodeCnt + 1]; // from에서 to로 가는 최단 경로를 k개 저장
		adjList = new ArrayList[nodeCnt + 1];
		for (int idx = 1; idx <= nodeCnt; idx++) {
			dist[idx] = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
			adjList[idx] = new ArrayList<>();
		}
		dist[START].offer(0);

		// 간선 입력
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adjList[from].add(new int[] { cost, to });
		}

		// 우선순위 큐에 시작점 추가
		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[COST], o2[COST]));
		pq.offer(new int[] { 0, START });

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();

			for (int[] next : adjList[cur[NODE]]) {
				int nextCost = cur[COST] + next[COST];
				// 최단 경로 추가
				// 현재 경로가 k개 미만이거나 기존보다 작은 경로로 교환
				if (dist[next[NODE]].size() < k || dist[next[NODE]].peek() > nextCost) {
					dist[next[NODE]].offer(nextCost);
					pq.offer(new int[] { nextCost, next[NODE] });
				}

				// k개 유지
				if (dist[next[NODE]].size() > k) {
					dist[next[NODE]].poll();
				}
			}
		}

		for (int idx = 1; idx <= nodeCnt; idx++) {
			if (dist[idx].size() == k) sb.append(dist[idx].poll() + "\n");
			else sb.append("-1\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
