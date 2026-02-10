import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int nodeCnt, start;
	static int[][] adj;
	static boolean[] visited;
	static int ans = Integer.MAX_VALUE;

	static void dfs(int cur, int visitedCnt, int totalTime) {
		if (visitedCnt == nodeCnt) {
			ans = Math.min(ans, totalTime);
			return;
		}

		for (int next = 0; next < nodeCnt; next++) {
			if (next == cur || visited[next]) continue;
			visited[next] = true;
			dfs(next, visitedCnt + 1, totalTime + adj[cur][next]);
			visited[next] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());

		adj = new int[nodeCnt][nodeCnt];
		for (int row = 0; row < nodeCnt; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < nodeCnt; col++) {
				adj[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		for (int mid = 0; mid < nodeCnt; mid++) {
			for (int from = 0; from < nodeCnt; from++) {
				for (int to = 0; to < nodeCnt; to++) {
					adj[from][to] = Math.min(adj[from][to], adj[from][mid] + adj[mid][to]);
				}
			}
		}

		visited = new boolean[nodeCnt];
		visited[start] = true;
		dfs(start, 1, 0);

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
