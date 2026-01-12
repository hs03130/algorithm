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
	static ArrayList<Integer>[] adjList;
	static int[] weights, prefixSums;
	static int ans = 0;
	static final int ROOT = 1;

	static void dfs(int node) {
		if (adjList[node] == null) return;

		int firstPrefixSum = 0, secondPrefixSum = 0;
		for (int next : adjList[node]) {
			dfs(next);
			int childPrefixSum = prefixSums[next] + weights[next];
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
		weights = new int[nodeCnt + 1];
		prefixSums = new int[nodeCnt + 1];

		for (int idx = 1; idx < nodeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if (adjList[parent] == null) adjList[parent] = new ArrayList<>();
			adjList[parent].add(child);
			weights[child] = weight;
		}

		dfs(ROOT);

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
