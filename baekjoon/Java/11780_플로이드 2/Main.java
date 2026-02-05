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
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt, edgeCnt;
	static int[][] adj, stopover;

	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());
		edgeCnt = Integer.parseInt(br.readLine());

		adj = new int[nodeCnt + 1][nodeCnt + 1];
		stopover = new int[nodeCnt + 1][nodeCnt + 1];

		// 비용 초기화
		for (int idx = 1; idx <= nodeCnt; idx++) {
			Arrays.fill(adj[idx], INF);
			adj[idx][idx] = 0;
		}

		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adj[from][to] = Math.min(adj[from][to], cost); // 노선은 하나 이상일 수 있음

			stopover[from][to] = to;
		}

		// from에서 to로 갈 때 mid를 거치는 것과 비교하면서 최소 비용 찾기
		for (int mid = 1; mid <= nodeCnt; mid++) {
			for (int from = 1; from <= nodeCnt; from++) {
				for (int to = 1; to <= nodeCnt; to++) {
					if (adj[from][to] > adj[from][mid] + adj[mid][to]) {
						adj[from][to] = adj[from][mid] + adj[mid][to];

						// from에서 to까지 가기 위한 최소 비용 경로에서 다음에 방문할 곳
						stopover[from][to] = stopover[from][mid];
					}
				}
			}
		}

		// 비용 출력
		for (int from = 1; from <= nodeCnt; from++) {
			for (int to = 1; to <= nodeCnt; to++) {
				if (adj[from][to] == INF) { // 갈 수 없는 경우 0 출력
					sb.append("0 ");
				} else {
					sb.append(adj[from][to] + " ");
				}
			}
			sb.append("\n");
		}

		// 최소 비용 경로에 포함된 도시 개수와 경로 출력
		for (int from = 1; from <= nodeCnt; from++) {
			for (int to = 1; to <= nodeCnt; to++) {
				if (adj[from][to] == INF || adj[from][to] == 0) { // 갈 수 없는 경우 0 출력
					sb.append("0");
				} else {
					ArrayList<Integer> path = new ArrayList<>();

					// 출발
					path.add(from);

					// 경유지
					int next = stopover[from][to];
					while (next != to) {
						path.add(next); // 경유지 추가
						next = stopover[next][to];
					}

					// 도착
					path.add(to);

					// 최소 비용 경로에 포함된 도시 개수 출력
					sb.append(path.size());

					// 경로 출력
					for (int num : path) {
						sb.append(" " + num);
					}
				}
				sb.append("\n");
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
