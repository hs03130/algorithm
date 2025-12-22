import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int testCase, nodeCnt, edgeCnt;
	static ArrayList<Integer>[] adjList;
	static int[] group;

	static boolean bfs() {
		ArrayDeque<Integer> Q = new ArrayDeque<>();
		for (int start = 0; start < nodeCnt; start++) {
			if (group[start] != 0)
				continue;

			group[start] = 1; // 새로 확인하는 정점은 기존 그룹 1 정점들과 인접하지 않음
			Q.offer(start);

			// bfs로 start와 직/간접적으로 인접하는 정점들의 그룹을 정함
			while (!Q.isEmpty()) {
				int u = Q.poll();
				
				if (adjList[u] == null) continue;
				// u와 인접하는 정점들
				for (int v : adjList[u]) {
					// 이미 속한 그룹이 있는 경우
					if (group[v] != 0) {
						// u와 다른 그룹인지 확인
						if (group[u] == group[v]) {
							return false;
						}
					}
					// 속한 그룹이 없는 경우
					else {
						// u와 다른 그룹으로 넣고 큐에 삽입
						group[v] = group[u] == 1 ? 2 : 1;
						Q.offer(v);
					}
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			st = new StringTokenizer(br.readLine());
			nodeCnt = Integer.parseInt(st.nextToken());
			if (nodeCnt == 1) {
				sb.append("NO\n");
				continue;
			}
			edgeCnt = Integer.parseInt(st.nextToken());

			adjList = new ArrayList[nodeCnt];
			for (int idx = 0; idx < edgeCnt; idx++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				if (adjList[u] == null)
					adjList[u] = new ArrayList<>();
				if (adjList[v] == null)
					adjList[v] = new ArrayList<>();
				adjList[u].add(v);
				adjList[v].add(u);
			}

			group = new int[nodeCnt];
			if (bfs()) {
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
