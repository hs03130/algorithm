/*
 * dfs
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int nodeCnt;
	static int edgeCnt;
	static int[][] adjMatrix; // 무향 그래프
	static int[] visit;

	static int ans;

	public static void main(String[] args) throws Exception {
		// 입력
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		adjMatrix = new int[nodeCnt][nodeCnt];
		for (int row = 0; row < edgeCnt; row++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			adjMatrix[from][to] = 1;
			adjMatrix[to][from] = 1;
		}

		// 풀이
		ans = 0;
		visit = new int[nodeCnt];

		for (int idx = 0; idx < nodeCnt; idx++) {
			if (visit[idx] == 0) {
				ans++;
				visit[idx] = 1;
				dfs(idx);
			}
		}

		// 출력
		System.out.println(ans);
	}

	static void dfs(int from) {
		// 현재 정점과 연결된 모든 미방문 정점을 방문
		for (int to = 0; to < nodeCnt; to++) {
			if (adjMatrix[from][to] == 1 && visit[to] == 0) {
				visit[to] = 1;
				dfs(to);
			}
		}
	}

}