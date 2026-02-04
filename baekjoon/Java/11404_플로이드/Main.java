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

	static int nodeCnt, edgeCnt;
	static int[][] adj;

	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());
		edgeCnt = Integer.parseInt(br.readLine());

		adj = new int[nodeCnt][nodeCnt];
		for (int idx = 0; idx < nodeCnt; idx++) {
			Arrays.fill(adj[idx], INF);
			adj[idx][idx] = 0;
		}

		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());

			adj[from][to] = Math.min(adj[from][to], cost); // 노선은 하나 이상일 수 있음
		}

		for (int mid = 0; mid < nodeCnt; mid++) {
			for (int from = 0; from < nodeCnt; from++) {
				for (int to = 0; to < nodeCnt; to++) {
					adj[from][to] = Math.min(adj[from][to], adj[from][mid] + adj[mid][to]);
				}
			}
		}

		for (int row = 0; row < nodeCnt; row++) {
			for (int col = 0; col < nodeCnt; col++) {
				if (adj[row][col] == INF) { // 갈 수 없는 경우 0 출력
					sb.append("0 ");
				} else {
					sb.append(adj[row][col] + " ");
				}
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
