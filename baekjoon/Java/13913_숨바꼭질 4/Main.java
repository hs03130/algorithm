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
	static int[] dist, prev;
	static ArrayDeque<Integer> Q;

	static final int MIN_X = 0, MAX_X = 100000, NOT_VISITED = -1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		subin = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());

		dist = new int[MAX_X + 1];
		prev = new int[MAX_X + 1];
		Arrays.fill(dist, NOT_VISITED);

		Q = new ArrayDeque<>();

		// 출발점
		dist[subin] = 0;
		Q.offer(subin);

		while (dist[target] == NOT_VISITED) {
			int cur = Q.poll();

			for (int nx : new int[] { cur + 1, cur - 1, cur * 2 }) {
				// 범위 벗어남
				if (nx < MIN_X || nx > MAX_X) continue;

				// 이미 방문함
				if (dist[nx] != NOT_VISITED) continue;

				// 시간 기록
				dist[nx] = dist[cur] + 1;
				prev[nx] = cur;
				Q.offer(nx);
			}
		}

		// 출력
		sb.append(dist[target] + "\n");

		ArrayDeque<Integer> stack = new ArrayDeque<>();
		stack.push(target);
		int curIdx = target;
		while (curIdx != subin) {
			stack.push(prev[curIdx]);
			curIdx = prev[curIdx];
		}
		while (!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}