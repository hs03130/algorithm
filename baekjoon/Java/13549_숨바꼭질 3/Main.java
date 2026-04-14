import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int subin, target;
	static int[] dist;
	static ArrayDeque<Integer> Q;

	static final int MIN_X = 0, MAX_X = 100000, NOT_VISITED = -1;
	static final int[] dx = { 1, -1 };

	// 현재 지점 x에서 x*2, x*4, ... 위치에 현재 시간 기록
	static void teleport(int x) {
		int cur = x << 1;
		while (cur >= MIN_X && cur <= MAX_X && dist[cur] == NOT_VISITED) {
			dist[cur] = dist[x];
			if (cur == target) return;
			Q.offer(cur);
			cur <<= 1;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		subin = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());

		dist = new int[MAX_X + 1];
		Arrays.fill(dist, NOT_VISITED);

		Q = new ArrayDeque<>();

		// 출발점
		dist[subin] = 0;
		Q.offer(subin);
		teleport(subin);

		while (dist[target] == NOT_VISITED) {
			int cur = Q.poll();

			for (int dir = 0; dir < 2; dir++) {
				int nx = cur + dx[dir];

				// 범위 벗어남
				if (nx < MIN_X || nx > MAX_X) continue;

				// 이미 방문함
				if (dist[nx] != NOT_VISITED) continue;

				// 시간 기록 후 순간이동 수행
				dist[nx] = dist[cur] + 1;
				Q.offer(nx);
				if (nx != target) teleport(nx);
			}
		}

		bw.write(String.valueOf(dist[target]));
		bw.flush();
		bw.close();
	}

}