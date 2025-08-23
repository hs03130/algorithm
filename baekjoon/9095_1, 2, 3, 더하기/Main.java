import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int number;
	static int[] cache;

	static final int MAX_NUMBER = 11;

	public static void main(String[] args) throws Exception {
		cache = new int[MAX_NUMBER];
		cache[1] = 1;
		cache[2] = 2;
		cache[3] = 4;

		for (int num = 4; num < MAX_NUMBER; num++) {
			cache[num] = cache[num - 1] + cache[num - 2] + cache[num - 3];
		}

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			number = Integer.parseInt(br.readLine());

			// 출력
			System.out.println(cache[number]);
		}
	}

}