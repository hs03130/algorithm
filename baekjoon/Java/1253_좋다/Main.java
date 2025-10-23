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
	static int[] nums;
	static boolean[] checked;
	static int ans = 0;

	static int lowerBound(int num) {
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

	static int upperBound(int num) {
		int left = -1, right = N;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (nums[mid] <= num) {
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
		Arrays.sort(nums);

		checked = new boolean[N];
		for (int row = 0; row < N - 1; row++) {
			for (int col = row + 1; col < N; col++) {
				// 두 수의 합
				int sum = nums[row] + nums[col];

				// 좋은 수 세기
				int upper = upperBound(sum);
				int lower = lowerBound(sum);
				for (int idx = lower; idx < upper; idx++) {
					if (idx == row || idx == col || checked[idx])
						continue;
					checked[idx] = true;
					ans++;
				}
			}
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}