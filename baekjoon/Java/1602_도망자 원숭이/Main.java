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
	static int[] cities;
	static Integer[] sortedCities;
	static int[][] adj, dist;

	static final int INF = Integer.MAX_VALUE / 3;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());
		queryCnt = Integer.parseInt(st.nextToken());

		cities = new int[nodeCnt + 1]; // 도시별 멍멍이가 원숭이를 괴롭히는 시간
		sortedCities = new Integer[nodeCnt]; // 괴롭히는 시간에 대해 오름차순으로 정렬한 도시
		adj = new int[nodeCnt + 1][nodeCnt + 1]; // 괴롭히는 시간을 고려하지 않은 최소 이동 시간
		dist = new int[nodeCnt + 1][nodeCnt + 1]; // 괴롭히는 시간을 고려한 최소 이동 시간

		// 도시별 멍멍이가 원숭이를 괴롭히는 시간 입력
		st = new StringTokenizer(br.readLine());
		for (int idx = 1; idx <= nodeCnt; idx++) {
			cities[idx] = Integer.parseInt(st.nextToken());
			sortedCities[idx - 1] = idx;
		}
		// 괴롭히는 시간에 대해 오름차순으로 정렬
		Arrays.sort(sortedCities, 0, nodeCnt, (o1, o2) -> Integer.compare(cities[o1], cities[o2]));

		// 이동 시간 초기화
		for (int idx = 1; idx <= nodeCnt; idx++) {
			Arrays.fill(adj[idx], INF);
			Arrays.fill(dist[idx], INF);

			adj[idx][idx] = 0; // 자기 자신까지의 이동 시간 0
			dist[idx][idx] = cities[idx]; // 괴롭힘 시간은 발생
		}

		// 이동 시간 입력
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adj[u][v] = cost;
			adj[v][u] = cost;

			dist[u][v] = cost + Math.max(cities[u], cities[v]);
			dist[v][u] = dist[u][v];
		}

		// from에서 to로 이동할 때 mid를 거치는 것과 비교하여 최소 비용 찾기
		for (int mid : sortedCities) { // mid는 괴롭히는 시간이 짧은 순서대로 고려
			for (int from = 1; from <= nodeCnt; from++) {
				for (int to = 1; to <= nodeCnt; to++) {
					adj[from][to] = Math.min(adj[from][to], adj[from][mid] + adj[mid][to]);

					// mid를 오름차순으로 고려하기 때문에 경로 중 괴롭히는 시간이 가장 긴 도시는 from, mid, to 중에 하나이다.
					int maxTime = Math.max(cities[mid], Math.max(cities[from], cities[to]));
					dist[from][to] = Math.min(dist[from][to], adj[from][to] + maxTime);
				}
			}
		}

		while (queryCnt-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			if (dist[from][to] >= INF) sb.append("-1\n");
			else sb.append(dist[from][to] + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
