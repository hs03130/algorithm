import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static long[] nums;
	static long ans1, ans2, ans3;

	static int lowerBound(long num) {
		int left = -1, right = N;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (nums[mid] < num) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return right;
	}

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		nums = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);

		ans1 = nums[0];
		ans2 = nums[1];
		ans3 = nums[2];
		for (int x = 0; x < N - 1; x++) {
			for (int y = x + 1; y < N; y++) {
				int z = lowerBound(-nums[x] - nums[y]);

				for (int k = -3; k <= 2; k++) {
					if (z + k == x || z + k == y)
						continue;
					if (z + k < 0 || z + k >= N)
						continue;
					if (Math.abs(ans1 + ans2 + ans3) > Math.abs(nums[x] + nums[y] + nums[z + k])) {
						ans1 = nums[x];
						ans2 = nums[y];
						ans3 = nums[z + k];
					}
				}
			}
		}

		sb.append(ans1 + " " + ans2 + " " + ans3);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
