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

	static int nodeCnt, root, colNumber = 1;
	static int[][] adj, level;
	static int ansLevel = 1, ansWidth = 1;

	static final int PARENT = 0, LEFT = 1, RIGHT = 2, MIN = 0, MAX = 1;

	static void inorder(int node, int depth) {
		if (adj[node][LEFT] != -1) {
			inorder(adj[node][LEFT], depth + 1);
		}

		if (level[depth][MIN] > colNumber) level[depth][MIN] = colNumber;
		if (level[depth][MAX] < colNumber) level[depth][MAX] = colNumber;
		colNumber++;

		if (adj[node][RIGHT] != -1) {
			inorder(adj[node][RIGHT], depth + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());

		adj = new int[nodeCnt + 1][3];
		for (int idx = 0; idx < nodeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			adj[node][LEFT] = left;
			adj[node][RIGHT] = right;
			if (left != -1) adj[left][PARENT] = node;
			if (right != -1) adj[right][PARENT] = node;
		}
		
		// 루트 찾기
		for (int node = 1; node <= nodeCnt; node++) {
			if (adj[node][PARENT] == 0) {
				root = node;
				break;
			}
		}

		// 행별 최소 열번호 초기화
		level = new int[nodeCnt + 1][2];
		for (int depth = 2; depth <= nodeCnt; depth++) {
			level[depth][MIN] = Integer.MAX_VALUE;
		}

		// 행별 최소/최대 열번호 갱신
		inorder(root, 1);

		// 행마다 넓이 계산
		for (int depth = 2; depth <= nodeCnt; depth++) {
			if (level[depth][MIN] == Integer.MAX_VALUE && level[depth][MAX] == 0) break;

			if (level[depth][MAX] - level[depth][MIN] + 1 > ansWidth) {
				ansWidth = level[depth][MAX] - level[depth][MIN] + 1;
				ansLevel = depth;
			}
		}

		bw.write(String.valueOf(ansLevel) + " " + String.valueOf(ansWidth));
		bw.flush();
		bw.close();
	}

}
