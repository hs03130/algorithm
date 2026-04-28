import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int fruitCnt;
	static int snakeLen;
	static List<Integer> fruit;
	static int ans;

	// ют╥б
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		fruitCnt = Integer.parseInt(st.nextToken());
		snakeLen = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine().trim());
		fruit = new ArrayList<>();
		for (int idx = 0; idx < fruitCnt; idx++) {
			int num = Integer.parseInt(st.nextToken());
			if (num <= snakeLen) {
				snakeLen++;
			} else {
				fruit.add(num);
			}
		}
	}

	static void solve() {
		Collections.sort(fruit);
		for (int idx = 0; idx < fruit.size(); idx++) {
			if (fruit.get(idx) > snakeLen) {
				ans = snakeLen;
				return;
			}
			snakeLen++;
		}
		ans = snakeLen;
	}

	public static void main(String args[]) throws IOException {
		input();
		solve();
		System.out.println(ans);
	}

}