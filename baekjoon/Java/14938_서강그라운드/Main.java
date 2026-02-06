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
	static StringTokenizer st;

	static int nodeCnt, range, edgeCnt;
	static int[][] adj; // 이동 길이
	static int[] items; // 해당 구역에서 얻을 수 있는 아이템 수
	static int ans = 0;

	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		range = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		adj = new int[nodeCnt + 1][nodeCnt + 1];
		items = new int[nodeCnt + 1];

		st = new StringTokenizer(br.readLine());
		for (int idx = 1; idx <= nodeCnt; idx++) {
			// 이동 길이 초기화
			Arrays.fill(adj[idx], INF);
			adj[idx][idx] = 0;

			// 각 구역 아이템 수 입력
			items[idx] = Integer.parseInt(st.nextToken());
		}

		// 길 길이 입력
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adj[u][v] = cost;
			adj[v][u] = cost;
		}

		// from에서 to로 이동할 때 mid를 거치는 것과 비교하여 최소 비용 찾기
		for (int mid = 1; mid <= nodeCnt; mid++) {
			for (int from = 1; from <= nodeCnt; from++) {
				for (int to = 1; to <= nodeCnt; to++) {
					adj[from][to] = Math.min(adj[from][to], adj[from][mid] + adj[mid][to]);
				}
			}
		}

		// 가장 많은 아이템을 획득할 수 있는 낙하 지역 찾기
		for (int from = 1; from <= nodeCnt; from++) {
			int itemCnt = 0;
			for (int to = 1; to <= nodeCnt; to++) {
				if (adj[from][to] <= range) {
					itemCnt += items[to];
				}
			}
			ans = Math.max(ans, itemCnt);
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
