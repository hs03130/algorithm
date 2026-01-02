import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int nodeCnt, endpoint = 1;
	static int[] leftChild, rightChild;
	static boolean ended = false;
	static int ans = -1;

	static final int ROOT = 1, MAX_NODE = 100000;

	static void dfs(int cur) {
		ans++;

		if (leftChild[cur] != -1) {
			dfs(leftChild[cur]);
			ans++;
		}

		if (rightChild[cur] != -1) {
			dfs(rightChild[cur]);
			if (!ended) {
				ans++;
			}
		}

		if (cur == endpoint) {
			ended = true;
		}
	}

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());

		leftChild = new int[MAX_NODE + 1];
		rightChild = new int[MAX_NODE + 1];

		for (int idx = 0; idx < nodeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int value = Integer.parseInt(st.nextToken());
			leftChild[value] = Integer.parseInt(st.nextToken());
			rightChild[value] = Integer.parseInt(st.nextToken());
		}

		while (rightChild[endpoint] != -1) {
			endpoint = rightChild[endpoint];
		}

		dfs(ROOT);

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
