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

	static int nodeCnt, edgeCnt;
	static ArrayList<int[]>[] adjList;
	static boolean[] visited;
	static PriorityQueue<int[]> pq;
	static int ans = 0;

	static final int COST = 0, NODE = 1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[nodeCnt + 1];
		visited = new boolean[nodeCnt + 1];

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

		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[COST], o2[COST]));
		pq.add(new int[] { 0, 1 }); // 임의의 정점부터 시작

		int visitedCnt = 0;
		while (visitedCnt < nodeCnt) {
			int[] cur = pq.poll();
			if (visited[cur[NODE]]) continue;

			visited[cur[NODE]] = true;
			visitedCnt++;
			ans += cur[COST];

			if (adjList[cur[NODE]] == null) continue;
			for (int[] next : adjList[cur[NODE]]) {
				if (!visited[next[NODE]]) {
					pq.add(next);
				}
			}
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
