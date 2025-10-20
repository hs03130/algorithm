import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[] nums;
	static int min = Integer.MAX_VALUE;
	static int ans1, ans2;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(st.nextToken());
		}

		int left = -1, right = N - 1;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (nums[mid] < 0)
				left = mid;
			else
				right = mid;
		}

		if (left > 0) {
			min = Math.abs(nums[left - 1] + nums[left]);
			ans1 = left - 1;
			ans2 = left;
		}
		if (right < N - 1 && nums[right] + nums[right + 1] < min) {
			min = Math.abs(nums[right] + nums[right + 1]);
			ans1 = right;
			ans2 = right + 1;
		}

		while (left >= 0 && right < N) {
			if (Math.abs(nums[left] + nums[right]) < min) {
				min = Math.abs(nums[left] + nums[right]);
				ans1 = left;
				ans2 = right;
			}

			if (Math.abs(nums[left]) < Math.abs(nums[right])) {
				left--;
			} else {
				right++;
			}
		}

		sb.append(nums[ans1] + " " + nums[ans2]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}