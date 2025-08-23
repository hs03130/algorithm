import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static Deque<Character> Q;
	static int wordCnt;
	static String word;
	static int ans = 0;

	public static void main(String[] args) throws IOException {

		wordCnt = Integer.parseInt(br.readLine());

		for (int w = 0; w < wordCnt; w++) {
			word = br.readLine();
			Q = new ArrayDeque<>();
			for (int idx = 0; idx < word.length(); idx++) {
				if (Q.isEmpty() || Q.peek() != word.charAt(idx)) {
					Q.push(word.charAt(idx));
				} else {
					Q.pop();
				}
			}
			if (Q.isEmpty()) {
				ans++;
			}
		}

		System.out.println(ans);
	}

}
