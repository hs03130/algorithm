import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt, edgeCnt;
	static ArrayList<Integer>[] adjList;
	static int[] indegrees;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[nodeCnt + 1];
		indegrees = new int[nodeCnt + 1];
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if (adjList[from] == null) adjList[from] = new ArrayList<>();
			adjList[from].add(to);
			indegrees[to]++;
		}

		pq = new PriorityQueue<>();
		for (int idx = 1; idx <= nodeCnt; idx++) {
			if (indegrees[idx] == 0) {
				pq.offer(idx);
			}
		}

		while (!pq.isEmpty()) {
			int cur = pq.poll();

			sb.append(cur + " ");

			if (adjList[cur] == null) continue;
			for (int next : adjList[cur]) {
				if (--indegrees[next] == 0) {
					pq.add(next);
				}
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
