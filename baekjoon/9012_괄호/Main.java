import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int testCase;
	static Deque<String> Q;

	public static void main(String[] args) throws IOException {

		testCase = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < testCase; tc++) {
			Q = new ArrayDeque<>();
			String ans = "YES";
			
			String line = br.readLine();
			for (int idx = 0; idx < line.length(); idx++) {
				char c = line.charAt(idx);
				if (c == '(') {
					Q.push(String.valueOf(c));
				} else {
					if (Q.isEmpty()) {
						ans = "NO";
						break;
					} else {
						Q.pop();
					}
				}
			}
			if (!Q.isEmpty()) ans = "NO";
			System.out.println(ans);
		}

	}

}