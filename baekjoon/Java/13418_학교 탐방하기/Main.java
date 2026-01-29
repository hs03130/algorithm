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

	static int nodeCnt, edgeCnt, startCost, maxCost, minCost;
	static ArrayList<int[]>[] adjList;
	static boolean[] visited;
	static PriorityQueue<int[]> pq;
	static int ans;

	static final int COST = 0, NODE = 1, UP = 0;

	public static int solve() {
		int visitedCnt = 0, upCnt = 0;
		visited = new boolean[nodeCnt + 1];

		pq.offer(new int[] { startCost, 1 });

		while (visitedCnt < nodeCnt) {
			int[] cur = pq.poll();
			if (visited[cur[NODE]]) continue;

			visited[cur[NODE]] = true;
			visitedCnt++;
			if (cur[COST] == UP) upCnt++;

			for (int[] next : adjList[cur[NODE]]) {
				if (visited[next[NODE]]) continue;
				pq.offer(next);
			}
		}

		return (int) Math.pow(upCnt, 2);
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[nodeCnt + 1];

		// 입구에서 1번 건물로 가는 길
		st = new StringTokenizer(br.readLine());
		st.nextToken(); st.nextToken();
		startCost = Integer.parseInt(st.nextToken());

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

		// 최악의 코스
		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[COST], o2[COST]));
		maxCost = solve();

		// 최적의 코스
		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[COST], o1[COST]));
		minCost = solve();

		ans = maxCost - minCost;

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
