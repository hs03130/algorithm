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

	static int nodeCnt, orderCnt, removeCnt = 0;
	static int[] indegrees;
	static ArrayList<Integer>[] adjList;
	static ArrayDeque<Integer> Q;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		orderCnt = Integer.parseInt(st.nextToken());

		indegrees = new int[nodeCnt + 1];
		adjList = new ArrayList[nodeCnt + 1];

		while (orderCnt-- > 0) {
			st = new StringTokenizer(br.readLine());
			int nCnt = Integer.parseInt(st.nextToken()) - 1;
			int prev = Integer.parseInt(st.nextToken());
			while (nCnt-- > 0) {
				int cur = Integer.parseInt(st.nextToken());
				if (adjList[prev] == null) adjList[prev] = new ArrayList<>();
				adjList[prev].add(cur);
				indegrees[cur]++;
				prev = cur;
			}
		}

		Q = new ArrayDeque<>();
		for (int idx = 1; idx <= nodeCnt; idx++) {
			if (indegrees[idx] == 0) {
				Q.add(idx);
			}
		}

		while (!Q.isEmpty()) {
			int cur = Q.poll();
			sb.append(cur + "\n");
			removeCnt++;

			if (adjList[cur] == null) continue;
			for (int next : adjList[cur]) {
				indegrees[next]--;
				if (indegrees[next] == 0) {
					Q.add(next);
				}
			}
		}

		if (removeCnt == nodeCnt) {
			bw.write(sb.toString());
		} else {
			bw.write("0");
		}

		bw.flush();
		bw.close();
	}

}
