import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static char[] line;
	static Deque<Character> Q;

	public static void main(String[] args) throws IOException {

		StringBuilder sb = new StringBuilder();
		
		line = br.readLine().toCharArray();
		while (line[0] != '.') {
			Q = new ArrayDeque<>();
			for (char c : line) {
				if (c == '(' || c == '[') {
					Q.push(c);
				} else if (c == ')') {
					if (Q.isEmpty() || Q.pop() != '(') {
						sb.append("no\n");
						break;
					}
				} else if (c == ']') {
					if (Q.isEmpty() || Q.pop() != '[') {
						sb.append("no\n");
						break;
					}
				} else if (c == '.') {
					if (Q.isEmpty()) {
						sb.append("yes\n");						
					} else {
						sb.append("no\n");
					}

				}
			}
			line = br.readLine().toCharArray();
		}
		
		System.out.println(sb);
	}

}
