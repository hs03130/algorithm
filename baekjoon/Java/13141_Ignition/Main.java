import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int nodeCnt, edgeCnt;
	static int[][] adj;
	static ArrayList<int[]> edgeList;
	static double ans = Integer.MAX_VALUE;

	static final int INF = Integer.MAX_VALUE / 2, U = 0, V = 1, COST = 2;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		adj = new int[nodeCnt + 1][nodeCnt + 1];
		edgeList = new ArrayList<>();

		// 이동 시간 초기화
		for (int idx = 1; idx <= nodeCnt; idx++) {
			Arrays.fill(adj[idx], INF);
			adj[idx][idx] = 0;
		}

		// 간선 입력
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adj[u][v] = Math.min(adj[u][v], cost);
			adj[v][u] = Math.min(adj[v][u], cost);

			edgeList.add(new int[] { u, v, cost });
		}

		// 정점간 최소 이동시간 계산
		for (int mid = 1; mid <= nodeCnt; mid++) {
			for (int from = 1; from <= nodeCnt; from++) {
				for (int to = 1; to <= nodeCnt; to++) {
					adj[from][to] = Math.min(adj[from][to], adj[from][mid] + adj[mid][to]);
				}
			}
		}

		// 각 시작 정점마다 그래프 전체가 타는 데 걸리는 시간 계산하고, 그 중 최소 시간 찾기
		for (int start = 1; start <= nodeCnt; start++) {
			// 간선별로 타는 시간을 계산
			// 가장 늦게 타는 간선의 시간이 그래프 전체가 다 타는 시간
			double timeToFireAll = 0;
			for (int[] edge : edgeList) {
				// adj[start][idx] : idx 정점에 불이 도달하는 시간
				// 간선이 타는 시간 : (U에 도달 시간 + V에 도달 시간 + 간선 길이) / 2
				double time = (double) (adj[start][edge[U]] + adj[start][edge[V]] + edge[COST]) / 2;
				timeToFireAll = Math.max(timeToFireAll, time);
			}
			ans = Math.min(ans, timeToFireAll);
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}