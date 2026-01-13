import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int nodeCnt;
	static ArrayList<int[]>[] adjList;
	static int[] prefixSums;
	static int ans = 0;
	static final int ROOT = 1, VALUE = 0, WEIGHT = 1;

	static void dfs(int node, int prev) {
		int firstPrefixSum = 0, secondPrefixSum = 0;
		for (int[] next : adjList[node]) {
			if (next[VALUE] == prev) continue;
			dfs(next[VALUE], node);
			int childPrefixSum = prefixSums[next[VALUE]] + next[WEIGHT];
			if (childPrefixSum > firstPrefixSum) {
				secondPrefixSum = firstPrefixSum;
				firstPrefixSum = childPrefixSum;
			} else if (childPrefixSum > secondPrefixSum) {
				secondPrefixSum = childPrefixSum;
			}
		}

		ans = Math.max(ans, firstPrefixSum + secondPrefixSum);
		prefixSums[node] = firstPrefixSum;
	}

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());
		adjList = new ArrayList[nodeCnt + 1];
		prefixSums = new int[nodeCnt + 1];

		for (int idx = 0; idx < nodeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			adjList[parent] = new ArrayList<>();
			int child = Integer.parseInt(st.nextToken());
			while (child != -1) {
				int weight = Integer.parseInt(st.nextToken());
				adjList[parent].add(new int[] { child, weight });
				child = Integer.parseInt(st.nextToken());
			}
		}

		dfs(ROOT, 0);

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
