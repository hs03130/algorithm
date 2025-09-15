import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int H;
	static int M;
	static int N;

	static int[][][] box;
	static int[][][] dist;
	static Deque<int[]> Q;

	static int ans;

	static int[] dx = { 1, 0, 0, -1, 0, 0 };
	static int[] dy = { 0, 1, 0, 0, -1, 0 };
	static int[] dz = { 0, 0, 1, 0, 0, -1 };
	static final int Z = 0;
	static final int Y = 1;
	static final int X = 2;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		box = new int[H][M][N];
		dist = new int[H][M][N];
		Q = new ArrayDeque<>();

		ans = 0;

		for (int z = 0; z < H; z++) {
			for (int y = 0; y < M; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					box[z][y][x] = Integer.parseInt(st.nextToken());

					if (box[z][y][x] == 1) { // 익은 토마토
						Q.offer(new int[] { z, y, x }); // BFS가 시작될 지점들을 미리 넣기
					}
					
					if (box[z][y][x] == 0) { // 안 익은 토마토 자리만 방문하기
						dist[z][y][x] = -1;
					}
				}
			}
		}

		while (!Q.isEmpty()) {
			int[] cur = Q.poll();

			for (int dir = 0; dir < 6; dir++) {
				int nz = cur[Z] + dz[dir];
				int ny = cur[Y] + dy[dir];
				int nx = cur[X] + dx[dir];

				if (nz < 0 || nz >= H || ny < 0 || ny >= M || nx < 0 || nx >= N) {
					continue;
				}

				if (dist[nz][ny][nx] >= 0) {
					continue;
				}

				dist[nz][ny][nx] = dist[cur[Z]][cur[Y]][cur[X]] + 1;
				Q.offer(new int[] { nz, ny, nx });

			}
		}

		for (int z = 0; z < H; z++) {
			for (int y = 0; y < M; y++) {
				for (int x = 0; x < N; x++) {
					if (dist[z][y][x] == -1) { // 익지 않은 토마토가 존재
						ans = -1;
						System.out.println(ans);
						return;
					}
					
					ans = Math.max(ans, dist[z][y][x]); // 토마토가 모두 익는데 걸리는 날짜
					// 모든 토마토가 이미 익어 있으면 ans는 0
				}
			}
		}
		
		System.out.println(ans);
	}

}
