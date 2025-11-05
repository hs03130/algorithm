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

	static int N, M;
	static int[] nums;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(nums);

		int left = 0, right = 0;
		while (left < N && right < N) {
			int value = nums[right] - nums[left];
			if (value < M) {
				right++;
			} else if (value == M) {
				ans = M;
				break;
			} else {
				ans = Math.min(ans, value);
				left++;
			}
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
