/*
 * 최단 경로 구하기
 * 1. 입력
 *  1-1 정점 개수
 *  1-2 인접 행렬
 * 2. 모든 도시를 방문하는 경로
 * 	2-1 임의의 점을 시작점으로하여 여행 경로를 만든다.
 *  2-2 이미 방문한 도시를 제외하고 남은 도시중 이동 가능한 도시로 방문한다.
 *  2-3 모든 도시를 방문했다면, 마지막 도시에서 다시 출발점으로 돌아올 수 있는지 확인한다.
 *  2-4 가능하다면 여행 경로를 완성한 것. 지금까지의 경로 길이와 최소값 비교
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int vertexCnt;
	static int[][] adjMatrix;
	static boolean[] visited;
	static int[] selectList;
	static int ans;

	// 입력
	static void input() throws Exception {
		vertexCnt = Integer.parseInt(br.readLine().trim());
		adjMatrix = new int[vertexCnt][vertexCnt];
		for (int from = 0; from < vertexCnt; from++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int to = 0; to < vertexCnt; to++) {
				adjMatrix[from][to] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void solve() {
		ans = Integer.MAX_VALUE;
		selectList = new int[vertexCnt];

		// 임의의 출발점부터 시작
		for (int start = 0; start < vertexCnt; start++) {
			visited = new boolean[vertexCnt];
			visited[start] = true;
			selectList[0] = start;
			func(start, 1, 0);
		}
	}

	// start에서 시작하여 모든 도시를 방문하기
	static void func(int start, int selectIdx, int cost) {
		if (cost > ans) {
			return;
		}
		
		if (selectIdx == vertexCnt) {
			// 마지막 도시에서 출발 도시로 갈 수 있다면 가능한 여행 경로
			if (adjMatrix[selectList[selectIdx - 1]][start] > 0) {
				// 마지막 도시에서 출발 도시로 가는 비용을 더해줌
				cost += adjMatrix[selectList[selectIdx - 1]][start];
				ans = Math.min(ans, cost);
			}
			return;
		}
		
		for (int idx = 0; idx < vertexCnt; idx++) {
			if (idx == start || visited[idx])
				continue;
			int weigth = adjMatrix[selectList[selectIdx - 1]][idx];
			if (weigth > 0) {
				visited[idx] = true;
				selectList[selectIdx] = idx;
				func(start, selectIdx + 1, cost + weigth);
				visited[idx] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		input();
		solve();
		System.out.println(ans);
	}

}