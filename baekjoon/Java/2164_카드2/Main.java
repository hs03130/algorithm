import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static Deque<Integer> Q;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		Q = new ArrayDeque<>();

		for (int idx = 1; idx <= N; idx++) {
			Q.offer(idx);
		}

		while (Q.size() > 1) {
			// ���� �� ī�� ������
			Q.poll();
			// ���� �� ī�带 �� �ڷ� ������
			Q.offer(Q.poll());
		}

		System.out.println(Q.peek());
	}

}
