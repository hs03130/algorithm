import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt, edgeCnt, testCnt;
	static int[][] adj;

	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());
		testCnt = Integer.parseInt(st.nextToken());

		adj = new int[nodeCnt + 1][nodeCnt + 1];

		for (int idx = 1; idx <= nodeCnt; idx++) {
			Arrays.fill(adj[idx], INF);
		}

		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adj[from][to] = Math.min(adj[from][to], cost);
		}

		for (int mid = 1; mid <= nodeCnt; mid++) {
			for (int from = 1; from <= nodeCnt; from++) {
				for (int to = 1; to <= nodeCnt; to++) {
					adj[from][to] = Math.min(adj[from][to], Math.max(adj[from][mid], adj[mid][to]));
				}
			}
		}

		while (testCnt-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			if (adj[from][to] == INF)
				sb.append("-1 \n");
			else
				sb.append(adj[from][to] + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
