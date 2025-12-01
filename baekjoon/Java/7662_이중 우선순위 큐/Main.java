import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int testCase;
	static int N;
	static PriorityQueue<Integer> minQ;
	static PriorityQueue<Integer> maxQ;
	static Map<Integer, Integer> map;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			N = Integer.parseInt(br.readLine());

			minQ = new PriorityQueue<>();
			maxQ = new PriorityQueue<>(Collections.reverseOrder());
			map = new HashMap<>();

			for (int idx = 0; idx < N; idx++) {
				st = new StringTokenizer(br.readLine());
				char cmd = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());

				if (cmd == 'I') {
					minQ.offer(num);
					maxQ.offer(num);
					map.put(num, map.getOrDefault(num, 0) + 1);
				} else {
					if (map.size() == 0)
						continue;

					if (num == -1) { // 최솟값 삭제
						delete(minQ);
					} else { // 최대값 삭제
						delete(maxQ);
					}
				}
			}

			if (map.size() == 0) {
				sb.append("EMPTY\n");
			} else {
				while (!maxQ.isEmpty()) {
					int num = maxQ.poll();
					if (map.getOrDefault(num, 0) > 0) {
						sb.append(num + " ");
						break;
					}
				}
				while (!minQ.isEmpty()) {
					int num = minQ.poll();
					if (map.getOrDefault(num, 0) > 0) {
						sb.append(num + "\n");
						break;
					}
				}
			}
		}
		System.out.println(sb);
	}

	static void delete(Queue<Integer> Q) {
		while (!Q.isEmpty()) {
			int num = Q.poll();

			int cnt = map.getOrDefault(num, 0);

			if (cnt == 0) {
				continue;
			} else if (cnt == 1) {
				map.remove(num);
				break;
			} else {
				map.put(num, cnt - 1);
				break;
			}
		}
	}
}
