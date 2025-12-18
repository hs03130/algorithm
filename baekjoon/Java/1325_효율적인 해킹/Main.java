import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Integer>[] adj;
	static boolean[] visited;

	static int dfs(int from) {
		visited[from] = true;

		int cnt = 1;
		for (int to : adj[from]) {
			if (!visited[to]) {
				cnt += dfs(to);
			}
		}

		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int nodeCnt = Integer.parseInt(st.nextToken());
		int edgeCnt = Integer.parseInt(st.nextToken());

		adj = new ArrayList[nodeCnt];
		for (int idx = 0; idx < nodeCnt; idx++) {
			adj[idx] = new ArrayList<>();
		}
		visited = new boolean[nodeCnt];
		int[] score = new int[nodeCnt];

		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			adj[to].add(from);
		}

		int maxScore = 0;
		for (int idx = 0; idx < nodeCnt; idx++) {
			Arrays.fill(visited, false);
			score[idx] = dfs(idx);
			if (score[idx] > maxScore) {
				maxScore = score[idx];
			}
		}

		for (int idx = 0; idx < nodeCnt; idx++) {
			if (score[idx] == maxScore)
				sb.append((idx + 1) + " ");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
