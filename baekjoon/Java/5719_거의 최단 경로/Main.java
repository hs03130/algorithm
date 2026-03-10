import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
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
	static int[] dist;
	static ArrayList<int[]>[] adjList, reverseAdjList;
	static PriorityQueue<int[]> pq;
	static boolean[] usableEdge;
	static ArrayDeque<int[]> Q;

	static final int COST = 0, NODE = 1, EDGE = 2;

	// start로 부터의 최단 경로 구하기
	static void solve() {
		// 최단 거리 초기화
		dist = new int[nodeCnt];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		// 우선순위 큐에 시작점 추가
		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[COST], o2[COST]));
		pq.add(new int[] { 0, start });

		// 다익스트라
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (dist[cur[NODE]] != cur[COST]) continue;

			for (int[] next : adjList[cur[NODE]]) {
				if (!usableEdge[next[EDGE]]) continue; // 이용할 수 없는 간선
				if (dist[next[NODE]] <= cur[COST] + next[COST]) continue;
				dist[next[NODE]] = cur[COST] + next[COST];
				pq.add(new int[] { dist[next[NODE]], next[NODE] });
			}
		}
	}

	public static void main(String[] args) throws IOException {
		while (true) {
			st = new StringTokenizer(br.readLine());
			nodeCnt = Integer.parseInt(st.nextToken());
			edgeCnt = Integer.parseInt(st.nextToken());

			if (nodeCnt == 0 && edgeCnt == 0) break; // 종료

			// 시작점, 도착점 입력
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());

			// 간선 입력
			adjList = new ArrayList[nodeCnt];
			reverseAdjList = new ArrayList[nodeCnt];
			for (int idx = 0; idx < nodeCnt; idx++) {
				adjList[idx] = new ArrayList<>();
				reverseAdjList[idx] = new ArrayList<>();
			}

			for (int idx = 0; idx < edgeCnt; idx++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				adjList[from].add(new int[] { cost, to, idx });
				reverseAdjList[to].add(new int[] { cost, from, idx });
			}

			// 이용 가능한 간선
			usableEdge = new boolean[edgeCnt];
			Arrays.fill(usableEdge, true); // 최단 경로를 구할때는 모두 사용 가능

			// 최단 경로 구하기
			solve();

			// 최단 경로를 역추적하면서 최단 경로에 포함되는 간선 제거하기
			Q = new ArrayDeque<>();
			Q.offer(new int[] { dist[end], end });
			while (!Q.isEmpty()) {
				int[] cur = Q.poll();

				for (int[] next : reverseAdjList[cur[NODE]]) {
					if (!usableEdge[next[EDGE]]) continue; // 이미 확인한 간선
					
					// 최단 경로에 포함된 간선
					if (cur[COST] - next[COST] == dist[next[NODE]]) {
						usableEdge[next[EDGE]] = false;
						Q.offer(new int[] { dist[next[NODE]], next[NODE] });
					}
				}
			}

			// 거의 최단 경로 구하기
			solve();

			// 거의 최단 경로 출력
			if (dist[end] == Integer.MAX_VALUE) sb.append("-1\n");
			else sb.append(dist[end] + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
