import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt, edgeCnt, personCnt;
	static int[][] adj;
	static int[] people;
	static int ansTime = Integer.MAX_VALUE;
	static ArrayList<Integer> ans = new ArrayList<>();

	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		adj = new int[nodeCnt + 1][nodeCnt + 1];
		for (int idx = 1; idx <= nodeCnt; idx++) {
			// 이동 시간 초기화
			Arrays.fill(adj[idx], INF);
			adj[idx][idx] = 0;
		}

		// 이동 시간 입력
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adj[from][to] = cost;
		}

		// from에서 to로 이동할 때 mid를 거치는 것과 비교하여 최소 비용 찾기
		for (int mid = 1; mid <= nodeCnt; mid++) {
			for (int from = 1; from <= nodeCnt; from++) {
				for (int to = 1; to <= nodeCnt; to++) {
					adj[from][to] = Math.min(adj[from][to], adj[from][mid] + adj[mid][to]);
				}
			}
		}

		// 친구들이 사는 도시
		personCnt = Integer.parseInt(br.readLine());
		people = new int[personCnt];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < personCnt; idx++) {
			people[idx] = Integer.parseInt(st.nextToken());
		}

		// 최대 왕복 시간 최소 도시 찾기
		for (int nodeIdx = 1; nodeIdx <= nodeCnt; nodeIdx++) {
			int time = 0;
			boolean isPossible = true;

			for (int person : people) {
				time = Math.max(time, adj[person][nodeIdx] + adj[nodeIdx][person]);
			}

			if (isPossible && ansTime > time) {
				ansTime = time;
				ans.clear();
			}
			if (ansTime == time) {
				ans.add(nodeIdx);
			}
		}

		ans.sort((o1, o2) -> Integer.compare(o1, o2));
		for (int num : ans) {
			sb.append(num + " ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
