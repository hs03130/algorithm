import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static final int MOD = 10000;
	static int testCase;
	static int start, target;
	static Queue<Node> Q;
	static int[] dist;
	static char[] commands = { 'D', 'S', 'L', 'R' };
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			target = Integer.parseInt(st.nextToken());
			dist = new int[10000];

			Q = new ArrayDeque<>();
			Q.offer(new Node(start, ""));

			while (!Q.isEmpty()) {
				Node cur = Q.poll();
				if (cur.num == target) {
					sb.append(cur.cmd + '\n');
					break;
				}

				for (int idx = 0; idx < 4; idx++) {
					int next = -1;
					char command = commands[idx];

					if (command == 'D') {
						next = cur.num * 2 % MOD;
					} else if (command == 'S') {
						next = cur.num == 0 ? 9999 : cur.num - 1;
					} else if (command == 'L') {
						next = cur.num % 1000 * 10 + cur.num / 1000;
					} else {
						next = cur.num % 10 * 1000 + cur.num / 10;
					}

					if (dist[next] == 0 || dist[next] > dist[cur.num] + 1) {
						dist[next] = dist[cur.num] + 1;
						Node nextNode = new Node(next, cur.cmd + command);
						Q.offer(nextNode);
					}
				}

			}
		}
		System.out.println(sb);
	}

	static class Node {
		int num;
		String cmd;

		public Node(int num, String cmd) {
			this.num = num;
			this.cmd = cmd;
		}
	}

}
