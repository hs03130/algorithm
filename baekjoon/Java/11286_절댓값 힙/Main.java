import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		pq = new PriorityQueue<>((a, b) -> {
			if (Math.abs(a) == Math.abs(b)) return Integer.compare(a, b);
			return Integer.compare(Math.abs(a), Math.abs(b));
		});

		StringBuilder sb = new StringBuilder();
		while (N-- > 0) {
			int cmd = Integer.parseInt(br.readLine());
			if (cmd == 0) {
				if (pq.isEmpty()) sb.append("0\n");
				else sb.append(pq.poll() + "\n");
			} else {
				pq.offer(cmd);
			}
		}

		System.out.println(sb);
	}

}
