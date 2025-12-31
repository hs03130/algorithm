import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt, root, queryCnt;
	static ArrayList<Integer>[] adjList; // 1-based
	static boolean[] visited;
	static int[] subTreeCnt;

	static int dfs(int cur) {
		visited[cur] = true;
		if (adjList[cur] != null) {
			for (int v : adjList[cur]) {
				if (visited[v]) continue;
				subTreeCnt[cur] += dfs(v);
			}
		}
		subTreeCnt[cur]++; // ÀÚ½Å
		return subTreeCnt[cur];
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		root = Integer.parseInt(st.nextToken());
		queryCnt = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[nodeCnt + 1];
		for (int idx = 0; idx < nodeCnt - 1; idx++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if (adjList[u] == null) adjList[u] = new ArrayList<>();
			if (adjList[v] == null) adjList[v] = new ArrayList<>();
			adjList[u].add(v);
			adjList[v].add(u);
		}

		visited = new boolean[nodeCnt + 1];
		subTreeCnt = new int[nodeCnt + 1];
		subTreeCnt[root] = dfs(root);
		for (int idx = 0; idx < queryCnt; idx++) {
			int u = Integer.parseInt(br.readLine());
			sb.append(subTreeCnt[u] + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
