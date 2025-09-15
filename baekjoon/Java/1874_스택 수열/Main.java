import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb;

	static int numCnt;
	static Deque<Integer> stack;
	static int num;
	static int target;

	// ют╥б
	static void input() throws Exception {
		numCnt = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
	}

	static void solve() throws Exception {
		stack = new ArrayDeque<>();
		num = 1;

		for (int cnt = 0; cnt < numCnt; cnt++) {
			target = Integer.parseInt(br.readLine());
			while(num <= target) {
				stack.offerLast(num++);
				sb.append("+\n");
			}
			if (stack.peekLast() == target) {
				stack.pollLast();
				sb.append("-\n");
			} else {
				sb = new StringBuilder();
				sb.append("NO");
				return;
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		input();
		solve();
		System.out.println(sb.toString());
	}

}