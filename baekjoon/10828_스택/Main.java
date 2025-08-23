import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static Deque<Integer> Q = new ArrayDeque<>();

	static final int CMD = 0;
	static final int NUMBER = 1;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			String[] line = br.readLine().split(" ");

			if (line[CMD].equals("push")) {
				Q.push(Integer.parseInt(line[NUMBER]));
			} else if (line[CMD].equals("pop")) {
				if (Q.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(Q.pop());
				}
			} else if (line[CMD].equals("size")) {
				System.out.println(Q.size());
			} else if (line[CMD].equals("empty")) {
				if (Q.isEmpty()) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
			} else if (line[CMD].equals("top")) {
				if (Q.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(Q.peek());
				}
			}
		}

	}

}
