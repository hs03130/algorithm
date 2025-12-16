import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int nodeCnt;
	static int edgeCnt;
	static boolean[][] adjMatrix;

	static boolean[] visit;
	static Deque<Integer> Q;
	static int ans;

	public static void main(String[] args) throws Exception {

		// 입력
		nodeCnt = Integer.parseInt(br.readLine());
		edgeCnt = Integer.parseInt(br.readLine());

		adjMatrix = new boolean[nodeCnt][nodeCnt];

		for (int cnt = 0; cnt < edgeCnt; cnt++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			adjMatrix[from][to] = true;
			adjMatrix[to][from] = true;
		}

		// 풀이
		ans = 0;
		visit = new boolean[nodeCnt];
		Q = new ArrayDeque<>();

		// 시작점 1번
		visit[0] = true;
		Q.offer(0);

		while (!Q.isEmpty()) {
			int fromIdx = Q.poll();
			// 현재 정점에서 접근 가능한 모든 정점
			for (int toIdx = 0; toIdx < nodeCnt; toIdx++) {
				if (adjMatrix[fromIdx][toIdx] && !visit[toIdx]) {
					visit[toIdx] = true;
					ans++;
					Q.offer(toIdx);
				}
			}
		}

		// 출력
		System.out.println(ans);
	}

}