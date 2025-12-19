import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt, edgeCnt;
	static ArrayList<Integer>[] adjList;
	static int[] visited;
	static int maxDist = 0, targetIdx = 0, targetCnt = 1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[nodeCnt];
		visited = new int[nodeCnt];
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;

			if (adjList[from] == null)
				adjList[from] = new ArrayList<>();
			adjList[from].add(to);

			if (adjList[to] == null)
				adjList[to] = new ArrayList<>();
			adjList[to].add(from);
		}

		// 1번 헛간에서 시작해서 가장 먼 헛간 찾기
		Arrays.fill(visited, -1);
		ArrayDeque<Integer> Q = new ArrayDeque<>();
		visited[0] = 0;
		Q.offer(0);
		while (!Q.isEmpty()) {
			int cur = Q.poll();
			ArrayList<Integer> adj = adjList[cur];
			if (adj == null)
				continue;

			for (int to : adj) {
				if (visited[to] == -1) {
					visited[to] = visited[cur] + 1;
					if (visited[to] > maxDist) {
						maxDist = visited[to];
						targetIdx = to;
						targetCnt = 1;
					} else if (visited[to] == maxDist) {
						targetIdx = Math.min(targetIdx, to);
						targetCnt++;
					}
					Q.offer(to);
				}
			}
		}

		// 숨어야하는 헛간 번호
		sb.append((targetIdx + 1) + " ");

		// 숨어야하는 헛간까지의 거리
		sb.append(maxDist + " ");

		// 거리가 같은 헛간 개수
		sb.append(targetCnt);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
