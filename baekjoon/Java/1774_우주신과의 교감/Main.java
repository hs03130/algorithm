import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int nodeCnt, edgeCnt, visitedCnt = 0;
	static int[][] board;
	static double[][] adj;
	static boolean[] visited;
	static PriorityQueue<double[]> pq;
	static double ans;

	static final int COST = 0, NODE = 1, X = 0, Y = 1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		board = new int[nodeCnt + 1][2];
		adj = new double[nodeCnt + 1][nodeCnt + 1];
		visited = new boolean[nodeCnt + 1];

		// 우주신의 위치
		for (int idx = 1; idx <= nodeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[idx][X] = x;
			board[idx][Y] = y;
		}

		// 우주신들간 거리 계산
		for (int row = 1; row <= nodeCnt; row++) {
			for (int col = 1; col <= nodeCnt; col++) {
				double x = Math.pow(board[row][X] - board[col][X], 2);
				double y = Math.pow(board[row][Y] - board[col][Y], 2);
				double dist = Math.sqrt(x + y);
				adj[row][col] = dist;
				adj[col][row] = dist;
			}
		}

		// 이미 연결된 통로
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u][v] = 0;
			adj[v][u] = 0;
		}

		pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1[COST], o2[COST]));
		pq.offer(new double[] { 0, 1 });

		while (visitedCnt < nodeCnt) {
			double[] cur = pq.poll();
			if (visited[(int) cur[NODE]]) continue;

			visited[(int) cur[NODE]] = true;
			visitedCnt++;
			ans += cur[COST];

			for (int next = 1; next <= nodeCnt; next++) {
				if (visited[next]) continue;
				pq.offer(new double[] { adj[(int) cur[NODE]][next], next });
			}
		}

		bw.write(String.format("%.2f", ans));
		bw.flush();
		bw.close();
	}

}
