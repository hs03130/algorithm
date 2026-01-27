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

	static int nodeCnt, visitedCnt = 0;
	static int[][] adj;
	static boolean[] visited;
	static PriorityQueue<int[]> pq;
	static long ans = 0L;

	static final int COST = 0, NODE = 1;

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());

		adj = new int[nodeCnt][nodeCnt];
		visited = new boolean[nodeCnt];

		for (int row = 0; row < nodeCnt; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < nodeCnt; col++) {
				adj[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[COST], o2[COST]));
		pq.add(new int[] { 0, 0 });
		while (visitedCnt < nodeCnt) {
			int[] cur = pq.poll();
			if (visited[cur[NODE]]) continue;

			visited[cur[NODE]] = true;
			visitedCnt++;
			ans += cur[COST];

			for (int next = 0; next < nodeCnt; next++) {
				if (visited[next]) continue;
				pq.add(new int[] { adj[cur[NODE]][next], next });
			}
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
