import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt;
	static boolean[][] adj;
	static ArrayDeque<int[]> Q;
	static boolean[] visited;
	static int[] score;
	static int minScore = Integer.MAX_VALUE, minCnt = 0;

	static final int FROM = 0, DEPTH = 1;

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());
		adj = new boolean[nodeCnt][nodeCnt];

		st = new StringTokenizer(br.readLine());
		int u = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		while (u != -1 && v != -1) {
			adj[u - 1][v - 1] = true;
			adj[v - 1][u - 1] = true;

			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
		}

		score = new int[nodeCnt];
		for (int start = 0; start < nodeCnt; start++) {
			visited = new boolean[nodeCnt];
			Q = new ArrayDeque<>();

			visited[start] = true;
			Q.offer(new int[] { start, 0 });
			while (!Q.isEmpty()) {
				int[] cur = Q.poll();
				if (cur[DEPTH] > score[start])
					score[start] = cur[DEPTH];

				for (int to = 0; to < nodeCnt; to++) {
					if (to != cur[FROM] && !visited[to] && adj[cur[FROM]][to]) {
						visited[to] = true;
						Q.offer(new int[] { to, cur[DEPTH] + 1 });
					}
				}
			}

			if (score[start] < minScore) {
				minScore = score[start];
				minCnt = 1;
			} else if (score[start] == minScore) {
				minCnt++;
			}
		}

		sb.append(minScore + " " + minCnt + "\n");
		for (int idx = 0; idx < nodeCnt; idx++) {
			if (score[idx] == minScore) {
				sb.append((idx + 1) + " ");
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
