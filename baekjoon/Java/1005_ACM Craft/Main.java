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

	static int testCnt, nodeCnt, edgeCnt, targetNum;
	static int[] workTimes, finishTimes, indegrees;
	static ArrayList<Integer>[] adjList;
	static ArrayDeque<Integer> Q;

	public static void main(String[] args) throws IOException {
		testCnt = Integer.parseInt(br.readLine());
		while (testCnt-- > 0) {
			st = new StringTokenizer(br.readLine());
			nodeCnt = Integer.parseInt(st.nextToken());
			edgeCnt = Integer.parseInt(st.nextToken());

			workTimes = new int[nodeCnt + 1];
			finishTimes = new int[nodeCnt + 1];
			indegrees = new int[nodeCnt + 1];
			adjList = new ArrayList[nodeCnt + 1];

			st = new StringTokenizer(br.readLine());
			for (int node = 1; node <= nodeCnt; node++) {
				workTimes[node] = Integer.parseInt(st.nextToken());
			}

			for (int idx = 0; idx < edgeCnt; idx++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				if (adjList[from] == null) adjList[from] = new ArrayList<>();
				adjList[from].add(to);
				indegrees[to]++;
			}

			targetNum = Integer.parseInt(br.readLine());

			Q = new ArrayDeque<>();
			for (int node = 1; node <= nodeCnt; node++) {
				if (indegrees[node] == 0) {
					finishTimes[node] = workTimes[node];
					Q.add(node);
				}
			}

			while (!Q.isEmpty()) {
				int cur = Q.poll();
				if (adjList[cur] == null) continue;
				for (int next : adjList[cur]) {
					if (finishTimes[next] < finishTimes[cur] + workTimes[next])
						finishTimes[next] = finishTimes[cur] + workTimes[next];
					if (--indegrees[next] == 0) {
						Q.add(next);
					}
				}
			}

			sb.append(finishTimes[targetNum] + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
