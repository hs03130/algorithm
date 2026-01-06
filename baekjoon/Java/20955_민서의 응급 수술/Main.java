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
	static StringTokenizer st;

	static int nodeCnt, edgeCnt;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static int ans = -1;

	static void dfs(int cur, int prev) {
		visited[cur] = true;

		if (adjList[cur] != null) {
			while(!adjList[cur].isEmpty()) {
				int next = adjList[cur].remove(0);
				if (next == prev) continue;
				
				// 사이클 존재 -> 시냅스 연결 해제
				if (visited[next]) {
					ans++;
					adjList[next].remove(adjList[next].indexOf(cur));
					continue;
				}
				
				dfs(next, cur);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[nodeCnt + 1];
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if (adjList[u] == null) adjList[u] = new ArrayList<>();
			if (adjList[v] == null) adjList[v] = new ArrayList<>();
			adjList[u].add(v);
			adjList[v].add(u);
		}

		visited = new boolean[nodeCnt + 1];
		for (int idx = 1; idx <= nodeCnt; idx++) {
			if (visited[idx]) continue;

			// 새로운 트리 시작 -> 기존 트리랑 시냅스 연결
			ans++;
			dfs(idx, 0);
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
