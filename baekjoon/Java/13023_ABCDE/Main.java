import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int vertexCnt;
	static int edgeCnt;
	static List<Integer>[] adjMatrix;
	static boolean[] visited;
	static int ans;
	static int SELECT_CNT = 5;

	// 입력
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		vertexCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		adjMatrix = new ArrayList[vertexCnt];
		for (int idx = 0; idx < vertexCnt; idx++) {
			adjMatrix[idx] = new ArrayList<>();
		}
		for (int edge = 0; edge < edgeCnt; edge++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjMatrix[from].add(to);
			adjMatrix[to].add(from);
		}
	}

	static void solve() {
		ans = 0;
		visited = new boolean[vertexCnt];
		for (int start = 0; start < vertexCnt; start++) {
			visited[start] = true;
			func(start, 1);
			if (ans == 1) {
				return;
			}
			visited[start] = false;
		}
	}

	static void func(int from, int selectIdx) {
		// ABCDE 존재
		if (selectIdx == SELECT_CNT) {
			ans = 1;
			return;
		}

		for (int to : adjMatrix[from]) {
			if (!visited[to]) {
				visited[to] = true;
				func(to, selectIdx + 1);
				visited[to] = false;
			}
		}
	}

	public static void main(String args[]) throws IOException {
		input();
		solve();
		System.out.println(ans);
	}

}