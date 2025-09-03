import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int target;
	static long[] cache;

	static long ans;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			target = Integer.parseInt(br.readLine());

			// 풀이
			ans = 0;

			if (target <= 3) {
				ans = 1;
			} else {
				cache = new long[target + 1];
				cache[1] = 1;
				cache[2] = 1;
				cache[3] = 1;

				for (int idx = 4; idx <= target; idx++) {
					cache[idx] = cache[idx-2] + cache[idx-3];
				}
				ans = cache[target];
			}

			// 출력
			System.out.println(ans);
		}
	}

}