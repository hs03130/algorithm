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
	static StringTokenizer st;

	static int subin, target;
	static int[][] dist;
	static ArrayDeque<int[]> Q;
	static int ans = -1;

	static final int MIN_X = 0, MAX_X = 500000, NOT_VISITED = -1, X = 0, TIME = 1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		subin = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());

		dist = new int[2][MAX_X + 1];
		Arrays.fill(dist[0], NOT_VISITED); // 짝수 시간 방문 기록
		Arrays.fill(dist[1], NOT_VISITED); // 홀수 시간 방문 기록

		Q = new ArrayDeque<>();

		// 출발점
		dist[0][subin] = 0;
		Q.offer(new int[] { subin, 0 });

		// 수빈이 이동 기록
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();

			for (int nx : new int[] { cur[X] * 2, cur[X] + 1, cur[X] - 1 }) {
				// 범위 벗어남
				if (nx < MIN_X || nx > MAX_X) continue;

				// 이미 방문함
				if (dist[(cur[TIME] + 1) % 2][nx] != NOT_VISITED) continue;

				// 시간 기록
				dist[(cur[TIME] + 1) % 2][nx] = cur[TIME] + 1;
				Q.offer(new int[] { nx, cur[TIME] + 1 });
			}
		}

		// 동생 이동하며 수빈이 잡을 수 있는지 확인
		for (int time = 0; target + time <= MAX_X; time++) {
			target += time;
			
			// 수빈이가 동생보다 먼저 도착했거나 같이 도착한 경우
			if (dist[time % 2][target] != NOT_VISITED && dist[time % 2][target] <= time) {
				ans = time;
				break;
			}
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}