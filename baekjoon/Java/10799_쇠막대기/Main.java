import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static char[] arr;
	static Deque<Character> Q;
	static int ans = 0;

	public static void main(String[] args) throws IOException {

		arr = br.readLine().toCharArray();

		Q = new ArrayDeque<>();

		for (int idx = 0; idx < arr.length; idx++) {
			if (arr[idx] == '(') {
				Q.push('(');
			} else {
				if (arr[idx - 1] == '(') { // 레이저
					Q.pop();
					ans += Q.size();
				} else { // 쇠막대 끝
					Q.pop();
					ans++;
				}
			}
		}

		System.out.println(ans);

	}

}
