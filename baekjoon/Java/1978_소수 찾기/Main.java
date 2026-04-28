import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[] nums;

	static int ans = 0;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < N; index++) {
			nums[index] = Integer.parseInt(st.nextToken());

			if (nums[index] == 2) {
				ans++;
			} else if (nums[index] > 1 && nums[index] % 2 != 0) {
				int flag = 1;
				for (int p = 3; p * p <= nums[index]; p += 2) {
					if (nums[index] % p == 0)
						flag = 0;
				}
				ans += flag;
			}
		}

		System.out.println(ans);

	}

}
