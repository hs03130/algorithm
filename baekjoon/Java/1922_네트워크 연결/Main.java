/*
 * 최소신장트리 만들기 PRIM
 * 1. 입력
 *  1-1 정점 개수
 *  1-2 간선 개수
 *  1-3 간선 정보
 * 
 * 2. 최소신장트리 만들기 PRIM
 *  2-1 임의의 점 0부터 시작
 *  2-2 비트리 정점중 가장 작은 간선 찾기
 *  2-3 해당 정점을 트리 정점에 포함시키고, 비용 추가
 *  2-4 해당 정점의 간선을 기존 간선과 비교하여 저장
 *  2-5 모든 정점을 포함시킬때까지 반복
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int vertexCnt; // 정점 개수
	static int edgeCnt; // 간선 개수
	static int[][] adjMatrix; // 간선 정보
	static boolean[] visited; // 트리 노드 포함 여부
	static int[] minEdges; // 해당 정점으로 가는 최소 간선
	static int cost; // 비용
	static int START = 0;

	// 입력
	static void input() throws IOException {
		vertexCnt = Integer.parseInt(br.readLine().trim());
		edgeCnt = Integer.parseInt(br.readLine().trim());
		// 인접 행렬
		adjMatrix = new int[vertexCnt][vertexCnt];
		for (int edge = 0; edge < edgeCnt; edge++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			adjMatrix[from][to] = weight;
			adjMatrix[to][from] = weight; // 무향 그래프
		}
	}

	// 최소 신장 트리 만들기 - PRIM
	static void solve() {
		cost = 0;
		visited = new boolean[vertexCnt];
		minEdges = new int[vertexCnt];
		Arrays.fill(minEdges, Integer.MAX_VALUE);

		// 임의의 점 0부터 시작
		minEdges[START] = 0;

		// 모든 정점을 포함할때까지 반복
		for (int selectIdx = 0; selectIdx < vertexCnt; selectIdx++) {
			// 트리 노트 -> 비트리 노드로 가는 간선 확인
			int minVertex = -1; // 간선 가중치가 가장 낮은 정점
			int minWeight = Integer.MAX_VALUE; // 가장 낮은 가중치
			for (int idx = 0; idx < vertexCnt; idx++) {
				if (!visited[idx] && minEdges[idx] < minWeight) {
					minWeight = minEdges[idx];
					minVertex = idx;
				}
			}
			
			// 해당 비트리 정점을 트리 정점에 포함
			visited[minVertex] = true;
			cost += minWeight;

			// 해당 정점의 인접 간선을 확인하면서
			for (int idx = 0; idx < vertexCnt; idx++) {
				// 비트리 정점과 인접합 간선이면 기존 값과 비교
				if (!visited[idx] && adjMatrix[minVertex][idx] > 0) {
					minEdges[idx] = Math.min(minEdges[idx], adjMatrix[minVertex][idx]);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(cost);
	}

}