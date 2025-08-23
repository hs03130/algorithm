import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int K;
	static int number;
	static Deque<Integer> stack;

	static int ans = 0;

	public static void main(String[] args) throws IOException {
        
		K = Integer.parseInt(br.readLine());

		stack = new ArrayDeque<>();
		for (int index = 0; index < K; index++) {
			number = Integer.parseInt(br.readLine());
			if (number == 0) {
				stack.pop();
			} else {
				stack.push(number);
			}
		}
		
		while (!stack.isEmpty()) {
			ans += stack.pop();
		}

		System.out.println(ans);

	}
}
