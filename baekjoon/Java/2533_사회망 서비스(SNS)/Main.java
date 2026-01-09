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

	static int nodeCnt;
	static ArrayList<Integer>[] adjList;
	static int ans = 0;

	static boolean dfs(int node, int prev) {
		// 리프 노드
		if (adjList[node].size() == 1 && adjList[node].get(0) == prev) {
			return false;
		}

		boolean AllChildIsEarlyAdaptor = true;
		for (int child : adjList[node]) {
			if (child == prev) continue;

			if (!dfs(child, node)) {
				// 얼리 아답터가 아닌 자식이 존재
				AllChildIsEarlyAdaptor = false;
			}
		}

		// 얼리 아답터가 아닌 자식이 존재하면 node는 얼리 아답터
		if (!AllChildIsEarlyAdaptor) ans++;
		
		return !AllChildIsEarlyAdaptor;
	}

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());
		adjList = new ArrayList[nodeCnt + 1];

		for (int idx = 1; idx < nodeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			if (adjList[u] == null) adjList[u] = new ArrayList<>();
			if (adjList[v] == null) adjList[v] = new ArrayList<>();
			adjList[u].add(v);
			adjList[v].add(u);
		}

		dfs(1, 0);

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
