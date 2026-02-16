import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int nodeCnt;
	static int[][] adj;
	static boolean[][] isNecessary;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());

		adj = new int[nodeCnt][nodeCnt];
		isNecessary = new boolean[nodeCnt][nodeCnt];

		for (int from = 0; from < nodeCnt; from++) {
			st = new StringTokenizer(br.readLine());
			for (int to = 0; to < nodeCnt; to++) {
				adj[from][to] = Integer.parseInt(st.nextToken());
			}
			Arrays.fill(isNecessary[from], true);
		}

		for (int mid = 0; mid < nodeCnt; mid++) {
			for (int from = 0; from < nodeCnt; from++) {
				for (int to = 0; to < nodeCnt; to++) {
					// 입력으로 주어진 이동 시간보다 더 빠른 경로가 존재하므로 불가능한 경우
					if (adj[from][to] > adj[from][mid] + adj[mid][to]) {
						ans = -1;
					}

					if (mid == from || mid == to) continue;

					// from에서 to로 이동하는데 mid를 거치는 것이 가장 빠르므로 from에서 to를 연결하는 도로는 필요하지 않음
					if (adj[from][to] == adj[from][mid] + adj[mid][to]) {
						isNecessary[from][to] = false;
					}
				}
			}
		}

		if (ans != -1) {
			for (int from = 0; from < nodeCnt; from++) {
				for (int to = from + 1; to < nodeCnt; to++) {
					if (isNecessary[from][to]) ans += adj[from][to];
				}
			}
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
