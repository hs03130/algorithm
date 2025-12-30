import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt;
	static List<List<Integer>> adj;
	static int[] parent;
	static ArrayDeque<int[]> Q;
	static final int PARENT = 0, CHILD = 1;

	// 메인 실행 로직 ------------------------------------
	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());
		parent = new int[nodeCnt + 1]; // 1-based
		adj = new ArrayList<>(); // 0-based
		for (int idx = 0; idx < nodeCnt; idx++) {
			adj.add(new ArrayList<>());
		}

		for (int idx = 0; idx < nodeCnt - 1; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj.get(from - 1).add(to);
			adj.get(to - 1).add(from);
		}

		Q = new ArrayDeque<>();
		Q.offer(new int[] { -1, 1 });
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();
			parent[cur[CHILD]] = cur[PARENT];
			for (int idx = 0; idx < adj.get(cur[CHILD] - 1).size(); idx++) {
				int nx = adj.get(cur[CHILD] - 1).get(idx);
				if (parent[nx] == 0) {
					Q.offer(new int[] { cur[CHILD], nx });
				}
			}
		}

		for (int node = 2; node <= nodeCnt; node++) {
			sb.append(parent[node] + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
