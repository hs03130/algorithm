import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static final int CELL_CNT = 100;

	static int ladderCnt, snakeCnt;
	static int[] adj;
	static int[] dist;
	static Deque<Integer> Q;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		ladderCnt = Integer.parseInt(st.nextToken());
		snakeCnt = Integer.parseInt(st.nextToken());

		adj = new int[CELL_CNT + 1];
		dist = new int[CELL_CNT + 1];
		for (int idx = 0; idx < ladderCnt + snakeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from] = to;
		}

		Q = new ArrayDeque<>();
		Q.offer(1);
		while (!Q.isEmpty()) {
			int cur = Q.poll();

			if (adj[cur] != 0) { // 바로 이동
				if (dist[adj[cur]] == 0 || dist[adj[cur]] > dist[cur]) {
					dist[adj[cur]] = dist[cur];
					Q.offer(adj[cur]);
				}

			} else { // 주사위 굴리기
				for (int dice = 1; dice <= 6; dice++) {
					if (cur + dice <= 100 && (dist[cur + dice] == 0 || dist[cur + dice] > dist[cur] + 1)) {
						dist[cur + dice] = dist[cur] + 1;
						Q.offer(cur + dice);
					}
				}
			}

		}

		System.out.println(dist[100]);
	}

}
