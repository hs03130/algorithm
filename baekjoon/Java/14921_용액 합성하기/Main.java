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
	static int ans = Integer.MAX_VALUE;

	static int lowerBound(int left, int num) {
		int right = N;
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
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(st.nextToken());
		}

		int positiveNumStartIdx = lowerBound(-1, 0);
		if (positiveNumStartIdx < N - 1) {
			ans = nums[positiveNumStartIdx] + nums[positiveNumStartIdx + 1];
		}
		positiveNumStartIdx = Math.min(positiveNumStartIdx, N - 1);

		for (int idx = 0; idx < positiveNumStartIdx; idx++) {
			int posIdx = lowerBound(idx, Math.abs(nums[idx]));
			if (posIdx < N && Math.abs(nums[idx] + nums[posIdx]) < Math.abs(ans)) {
				ans = nums[idx] + nums[posIdx];
			}
			if (posIdx > idx + 1 && Math.abs(nums[idx] + nums[posIdx - 1]) < Math.abs(ans)) {
				ans = nums[idx] + nums[posIdx - 1];
			}
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}