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
	static StringTokenizer st;

	static int nodeCnt, edgeCnt;
	static ArrayList<Integer>[] adjList;
	static int[] smallerCnt, biggerCnt;
	static boolean[] visited;
	static ArrayDeque<Integer> Q;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[nodeCnt];
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;

			if (adjList[from] == null) {
				adjList[from] = new ArrayList<Integer>();
			}
			adjList[from].add(to);
		}

		smallerCnt = new int[nodeCnt];
		biggerCnt = new int[nodeCnt];
		visited = new boolean[nodeCnt];
		Q = new ArrayDeque<>();
		for (int start = 0; start < nodeCnt; start++) {
			Arrays.fill(visited, false);
			visited[start] = true;
			Q.offer(start);
			while (!Q.isEmpty()) {
				int cur = Q.poll();
				if (adjList[cur] == null)
					continue;
				for (int v : adjList[cur]) {
					if (!visited[v]) {
						biggerCnt[start]++; // start보다 큰 v
						smallerCnt[v]++; // v보다 작은 start
						visited[v] = true;
						Q.offer(v);
					}
				}
			}
		}

		int n = (nodeCnt + 1) / 2;
		for (int idx = 0; idx < nodeCnt; idx++) {
			if (smallerCnt[idx] >= n) {
				ans++;
			}
			if (biggerCnt[idx] >= n) {
				ans++;
			}
		}

		bw.write(Integer.toString(ans));
		bw.flush();
		bw.close();
	}

}
