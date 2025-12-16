import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb; // dfs, bfs

	static int nodeCnt;
	static int edgeCnt;
	static int startNode;
	static int[][] adjMatrix;
	static boolean[] visit;
	static Deque<Integer> Q;

	public static void main(String[] args) throws Exception {
		// 입력
		st = new StringTokenizer(br.readLine());

		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());
		startNode = Integer.parseInt(st.nextToken());

		adjMatrix = new int[nodeCnt+1][nodeCnt+1];
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjMatrix[from][to] = 1;
			adjMatrix[to][from] = 1;
		}

		// 풀이
		sb = new StringBuilder();

		// DFS
		visit = new boolean[nodeCnt + 1];

		sb.append(startNode + " ");
		visit[startNode] = true;
		dfs(startNode);
		sb.append("\n");

		// BFS
		visit = new boolean[nodeCnt + 1];
		Q = new ArrayDeque<>();

		sb.append(startNode + " ");
		visit[startNode] = true;
		Q.offer(startNode);

		while (!Q.isEmpty()) {
			int curNode = Q.poll();

			for (int idx = 1; idx <= nodeCnt; idx++) {
				if (visit[idx] || idx == curNode || adjMatrix[curNode][idx] == 0) {
					continue;
				}
				sb.append(idx + " ");
				visit[idx] = true;
				Q.offer(idx);
			}
		}

		// 출력
		System.out.println(sb.toString());
	}

	static void dfs(int node) {
		for (int idx = 1; idx <= nodeCnt; idx++) {
			if (visit[idx] || node == idx || adjMatrix[node][idx] == 0) {
				continue;
			}
			sb.append(idx + " ");
			visit[idx] = true;
			dfs(idx);
		}
	}

}