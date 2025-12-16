import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt, edgeCnt;
	static boolean[][] adj;
	static boolean[] visited;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());
		edgeCnt = Integer.parseInt(br.readLine());

		adj = new boolean[nodeCnt][nodeCnt];
		visited = new boolean[nodeCnt];
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			adj[from][to] = true;
			adj[to][from] = true;
		}

		visited[0] = true;
		for (int row = 0; row < nodeCnt; row++) {
			// 상근이의 친구
			if (adj[0][row] == true) {
				if (!visited[row]) {
					visited[row] = true;
					ans++;
				}
				// 상근이의 친구의 친구
				for (int col = 0; col < nodeCnt; col++) {
					if (adj[row][col] == true && visited[col] == false) {
						visited[col] = true;
						ans++;
					}
				}
			}
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
