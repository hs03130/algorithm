import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt, edgeCnt;
	static ArrayList<Integer>[] adjList; // 1-based
	static boolean[] visited;
	static boolean isTree;

	static final int MAX_NODE_CNT = 500;

	static void dfs(int cur, int prev) {
		if (adjList[cur] == null) {
			return;
		}

		for (int v : adjList[cur]) {
			if (v == prev) continue;

			if (visited[v]) { // 사이클 존재
				isTree = false;
				// 트리가 아니지만 부분집합에 포함된 남은 정점도 이어서 확인
			} else {
				visited[v] = true;
				dfs(v, cur);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		visited = new boolean[MAX_NODE_CNT + 1];

		int testCase = 1;
		while (nodeCnt != 0) {
			adjList = new ArrayList[nodeCnt + 1];
			for (int idx = 0; idx < edgeCnt; idx++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());

				if (adjList[u] == null) {
					adjList[u] = new ArrayList<>();
				}
				if (adjList[v] == null) {
					adjList[v] = new ArrayList<>();
				}

				adjList[u].add(v);
				adjList[v].add(u);
			}

			Arrays.fill(visited, false);

			// 1~n 정점을 확인하면서 트리 세기
			int cnt = 0;
			for (int start = 1; start <= nodeCnt; start++) {
				if (visited[start]) continue;

				// 새로운 트리 시작
				isTree = true;
				visited[start] = true;
				dfs(start, -1); // 정점은 1~n까지
				if (isTree) cnt++;
			}

			if (cnt == 0) {
				sb.append("Case " + testCase++ + ": No trees.\n");
			} else if (cnt == 1) {
				sb.append("Case " + testCase++ + ": There is one tree.\n");
			} else {
				sb.append("Case " + testCase++ + ": A forest of " + cnt + " trees.\n");
			}

			st = new StringTokenizer(br.readLine());
			nodeCnt = Integer.parseInt(st.nextToken());
			edgeCnt = Integer.parseInt(st.nextToken());
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
