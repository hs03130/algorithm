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

	static int nodeCnt, edgeCnt, queryCnt;
	static int[][] adj; // from에서 to로 갈때 양방향 통행으로 바꿔야할 횟수

	static final int INF = Integer.MAX_VALUE / 2, ONE_WAY = 0;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		adj = new int[nodeCnt + 1][nodeCnt + 1];
		for (int idx = 1; idx <= nodeCnt; idx++) {
			Arrays.fill(adj[idx], INF);
			adj[idx][idx] = 0;
		}

		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int type = Integer.parseInt(st.nextToken());

			adj[from][to] = 0;
			adj[to][from] = 0;

			// from에서 to로 가는 길이 일방통행이면 to에서 from으로 갈때는 양방향 통행으로 바꿔야 함
			if (type == ONE_WAY) adj[to][from] = 1;
		}

		for (int mid = 1; mid <= nodeCnt; mid++) {
			for (int from = 1; from <= nodeCnt; from++) {
				for (int to = 1; to <= nodeCnt; to++) {
					adj[from][to] = Math.min(adj[from][to], adj[from][mid] + adj[mid][to]);
				}
			}
		}

		queryCnt = Integer.parseInt(br.readLine());
		for (int idx = 0; idx < queryCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			sb.append(adj[from][to] + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
