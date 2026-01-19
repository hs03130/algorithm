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
	static StringTokenizer st;

	static int nodeCnt;
	static int[] workTimes, finishTimes, indegrees;
	static ArrayList<Integer>[] adjList;
	static ArrayDeque<Integer> Q;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());
		workTimes = new int[nodeCnt + 1];
		finishTimes = new int[nodeCnt + 1];
		indegrees = new int[nodeCnt + 1];
		adjList = new ArrayList[nodeCnt + 1];

		for (int to = 1; to <= nodeCnt; to++) {
			st = new StringTokenizer(br.readLine());
			workTimes[to] = Integer.parseInt(st.nextToken());

			int edgeCnt = Integer.parseInt(st.nextToken());
			for (int edge = 0; edge < edgeCnt; edge++) {
				int from = Integer.parseInt(st.nextToken());
				if (adjList[from] == null) adjList[from] = new ArrayList<>();
				adjList[from].add(to);
				indegrees[to]++;
			}

		}

		Q = new ArrayDeque<>();
		for (int node = 1; node <= nodeCnt; node++) {
			if (indegrees[node] == 0) {
				finishTimes[node] = workTimes[node];
				ans = Math.max(ans, finishTimes[node]);
				Q.add(node);
			}
		}

		while (!Q.isEmpty()) {
			int cur = Q.poll();
			if (adjList[cur] == null) continue;
			for (int next : adjList[cur]) {
				if (finishTimes[next] < finishTimes[cur] + workTimes[next])
					finishTimes[next] = finishTimes[cur] + workTimes[next];
				if (finishTimes[next] > ans)
					ans = finishTimes[next];
				if (--indegrees[next] == 0) {
					Q.add(next);
				}
			}
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
