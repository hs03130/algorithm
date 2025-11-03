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
	static int[] nums, LIS;
	static int ans = 1;

	static int lowerBound(int num) {
		int left = -1, right = ans;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (LIS[mid] < num) {
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
		LIS = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(st.nextToken());
		}

		LIS[0] = nums[0];
		for (int idx = 1; idx < N; idx++) {
			if (LIS[ans - 1] < nums[idx]) {
				LIS[ans++] = nums[idx];
			} else {
				LIS[lowerBound(nums[idx])] = nums[idx];
			}
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
