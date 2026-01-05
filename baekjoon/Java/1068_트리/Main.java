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

	static int nodeCnt, root, removeNode;
	static int[] parents;
	static ArrayList<Integer>[] childs;
	static int ans = 0;

	static void dfs(int node) {
		if (node == removeNode) {
			return;
		}
		
		if (childs[node] != null && !childs[node].isEmpty()) {
			for (int child : childs[node]) {
				dfs(child);
			}
		} else {
			ans++;
		}
	}

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());

		parents = new int[nodeCnt];
		childs = new ArrayList[nodeCnt];

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < nodeCnt; idx++) {
			parents[idx] = Integer.parseInt(st.nextToken());
			if (parents[idx] == -1) {
				root = idx;
			} else {
				if (childs[parents[idx]] == null) childs[parents[idx]] = new ArrayList<>();
				childs[parents[idx]].add(idx);
			}
		}

		removeNode = Integer.parseInt(br.readLine());
		if (removeNode != root) {
			childs[parents[removeNode]].remove(childs[parents[removeNode]].indexOf(removeNode));
		}

		dfs(root);

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
