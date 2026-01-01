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

	static int nodeCnt, findCnt;
	static ArrayList<int[]>[] adjList;

	static boolean dfs(int cur, int prev, int target, int dist) {
		if (cur == target) {
			sb.append(dist + "\n");
			return true;
		}

		for (int[] next : adjList[cur]) {
			if (next[0] == prev) continue;
			if (dfs(next[0], cur, target, dist + next[1])) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		findCnt = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[nodeCnt + 1];
		for (int idx = 1; idx < nodeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			if (adjList[u] == null) adjList[u] = new ArrayList<>();
			if (adjList[v] == null) adjList[v] = new ArrayList<>();
			adjList[u].add(new int[] { v, dist });
			adjList[v].add(new int[] { u, dist });
		}

		for (int idx = 0; idx < findCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			dfs(u, -1, v, 0);
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
