import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int total, start, target, up, down;
	static int[] dist;
	static Deque<Integer> Q;
	static int[] dx;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());

		total = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		up = Integer.parseInt(st.nextToken());
		down = Integer.parseInt(st.nextToken());

		// 1ÃþºÎÅÍ totalÃþ
		dist = new int[total + 1];
		Arrays.fill(dist, -1);
		dx = new int[] { up, down * -1 };

		Q = new ArrayDeque<>();
		dist[start] = 0;
		Q.offer(start);

		while (!Q.isEmpty() && dist[target] == -1) {
			int cur = Q.pop();

			for (int dir = 0; dir < 2; dir++) {
				int nx = cur + dx[dir];

				if (nx < 1 || nx > total || dist[nx] != -1) {
					continue;
				}

				dist[nx] = dist[cur] + 1;
				Q.offer(nx);
			}
		}

		if (dist[target] == -1) {
			System.out.println("use the stairs");
		} else {
			System.out.println(dist[target]);
		}

	}

}
