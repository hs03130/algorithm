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

	static int nodeCnt, edgeCnt, improveCnt, start, end;
	static int[] tax;
	static int[][] dist;
	static ArrayList<int[]>[] adjList;
	static PriorityQueue<int[]> pq;

	static final int COST = 0, NODE = 1, EDGE_CNT = 2, INF = Integer.MAX_VALUE / 2;

	static boolean isDominated(int curCost, int curNode, int curEdgeCnt) {
		for (int idx = 0; idx < curEdgeCnt; idx++) {
			if (dist[curNode][idx] < curCost) return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());
		improveCnt = Integer.parseInt(st.nextToken());

		// 출발점, 도착점 입력
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		// 비용 초기화
		dist = new int[nodeCnt + 1][nodeCnt]; // dist[node][cnt] : cnt개의 간선을 이용한 경로의 최소 비용
		for (int idx = 1; idx <= nodeCnt; idx++) {
			Arrays.fill(dist[idx], INF);
		}
		dist[start][0] = 0;

		// 간선 입력
		adjList = new ArrayList[nodeCnt + 1];
		for (int idx = 1; idx <= nodeCnt; idx++) {
			adjList[idx] = new ArrayList<>();
		}
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adjList[u].add(new int[] { cost, v });
			adjList[v].add(new int[] { cost, u });
		}

		// 인상되는 세금 입력
		tax = new int[improveCnt + 1];
		for (int idx = 1; idx <= improveCnt; idx++) {
			tax[idx] = tax[idx - 1] + Integer.parseInt(br.readLine());
		}

		// 우선순위 큐에 출발점 입력
		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[COST], o2[COST]));
		pq.offer(new int[] { 0, start, 0 }); // 비용, 정점, 간선 개수

		// 다익스트라
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();

			if (dist[cur[NODE]][cur[EDGE_CNT]] != cur[COST]) continue;

			// 간선 최대 사용 (모든 정점 방문함). IndexOutOfBounds 방지
			if (cur[EDGE_CNT] == nodeCnt - 1) continue; 

			// 같은 노드에 더 작은 간선 수와 비용으로 방문한 기록이 있으면 cur는 버린다
			if (isDominated(cur[COST], cur[NODE], cur[EDGE_CNT])) continue;

			for (int[] next : adjList[cur[NODE]]) {
				if (dist[next[NODE]][cur[EDGE_CNT] + 1] <= cur[COST] + next[COST]) continue;
				dist[next[NODE]][cur[EDGE_CNT] + 1] = cur[COST] + next[COST];
				pq.offer(new int[] { cur[COST] + next[COST], next[NODE], cur[EDGE_CNT] + 1 });
			}
		}

		// 출력
		for (int idx = 0; idx <= improveCnt; idx++) {
			int minCost = INF;
			for (int edge = 1; edge < nodeCnt; edge++) {
				minCost = Math.min(minCost, dist[end][edge] + tax[idx] * edge);
			}
			sb.append(minCost + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
