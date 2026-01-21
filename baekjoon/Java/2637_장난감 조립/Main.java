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

	static int nodeCnt, edgeCnt;
	static int[] indegrees, componentCnt;
	static ArrayList<int[]>[] adjList;
	static ArrayDeque<Integer> Q;

	static final int NODE = 0, CNT = 1;

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());
		edgeCnt = Integer.parseInt(br.readLine());

		indegrees = new int[nodeCnt + 1];
		componentCnt = new int[nodeCnt + 1];
		adjList = new ArrayList[nodeCnt + 1];

		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			if (adjList[from] == null) adjList[from] = new ArrayList<>();
			adjList[from].add(new int[] { to, cnt });
			indegrees[to]++;
		}

		Q = new ArrayDeque<>();
		componentCnt[nodeCnt] = 1;
		Q.add(nodeCnt);

		while (!Q.isEmpty()) {
			int cur = Q.poll();

			if (adjList[cur] == null) continue;
			for (int[] next : adjList[cur]) {
				// 필요한 cur부품 개수 * cur 하나를 만드는데 필요한 next부품 개수
				componentCnt[next[NODE]] += componentCnt[cur] * next[CNT];
				indegrees[next[NODE]]--;
				if (indegrees[next[NODE]] == 0) {
					Q.add(next[NODE]);
				}
			}
		}

		for (int node = 1; node <= nodeCnt; node++) {
			if (adjList[node] == null) { // 기본 부품
				sb.append(node + " " + componentCnt[node] + "\n");
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
