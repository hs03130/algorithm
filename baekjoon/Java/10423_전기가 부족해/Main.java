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
	static StringTokenizer st;

	static int nodeCnt, edgeCnt, generatorCnt, visitedCnt = 0;
	static ArrayList<int[]>[] adjList;
	static boolean[] visited;
	static PriorityQueue<int[]> pq;
	static int ans;

	static final int COST = 0, NODE = 1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());
		generatorCnt = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[nodeCnt + 1];
		visited = new boolean[nodeCnt + 1];
		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[COST], o2[COST]));

		// 발전소가 건설된 도시
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < generatorCnt; idx++) {
			pq.offer(new int[] { 0, Integer.parseInt(st.nextToken()) });
		}

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

		while (visitedCnt < nodeCnt) {
			int[] cur = pq.poll();
			if (visited[cur[NODE]]) continue;

			visited[cur[NODE]] = true;
			visitedCnt++;
			ans += cur[COST];

			if (adjList[cur[NODE]] == null) continue;
			for (int[] next : adjList[cur[NODE]]) {
				if (visited[next[NODE]]) continue;
				pq.offer(next);
			}
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
