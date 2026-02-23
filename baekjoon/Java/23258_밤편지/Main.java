import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt, queryCnt;
	static int[][][] adj;

	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		queryCnt = Integer.parseInt(st.nextToken());

		adj = new int[nodeCnt + 1][nodeCnt + 1][nodeCnt + 1];

		for (int from = 1; from <= nodeCnt; from++) {
			st = new StringTokenizer(br.readLine());
			for (int to = 1; to <= nodeCnt; to++) {
				adj[from][to][0] = Integer.parseInt(st.nextToken());
				if (from != to && adj[from][to][0] == 0)
					adj[from][to][0] = INF;
			}
		}

		for (int mid = 1; mid <= nodeCnt; mid++) {
			for (int from = 1; from <= nodeCnt; from++) {
				for (int to = 1; to <= nodeCnt; to++) {
					// adj[from][to][mid - 1] : mid를 거치지 않고, mid 미만의 도시만 거쳐갈 때의 최소 시간

					// adj[from][mid][mid - 1] + adj[mid][to][mid - 1] : mid를 거쳐갈 때의 최소 시간
					// 즉, mid 미만의 도시만 거쳐서 from에서 mid로 이동 -> (mid 방문) -> mid 미만의 도시만 거쳐서 mid에서 to로 이동
					adj[from][to][mid] = Math.min(adj[from][to][mid - 1], adj[from][mid][mid - 1] + adj[mid][to][mid - 1]);
				}
			}
		}

		while (queryCnt-- > 0) {
			st = new StringTokenizer(br.readLine());
			int maxNode = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			// 2^C 미만으로 이슬을 먹기 위해서는 C 미만의 도시만 거쳐서 이동
			if (adj[from][to][maxNode - 1] == INF) sb.append("-1\n");
			else sb.append(adj[from][to][maxNode - 1] + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
