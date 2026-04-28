import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, K;
	static Deque<Integer> Q = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		sb.append("<");

		for (int idx = 1; idx <= N; idx++) {
			Q.offer(idx);
		}

		while (!Q.isEmpty()) {
			for (int idx = 0; idx < K - 1; idx++) {
				Q.offer(Q.pop());
			}
			sb.append(Q.pop() + ", ");
		}

		sb.delete(sb.length() - 2, sb.length());
		sb.append(">");
		System.out.println(sb);

	}

}
