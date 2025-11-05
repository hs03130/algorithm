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

	static int N, S;
	static int[] nums;
	static int ans = -1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(st.nextToken());
		}

		int left = 0, right = 0, sum = nums[left];
		while (left < N && right < N) {
			if (sum >= S) {
				if (ans == -1 || ans > right - left + 1) {
					ans = right - left + 1;
				}
				sum -= nums[left++];
			}

			if (sum < S || left > right) {
				right++;
				if (right < N) {
					sum += nums[right];
				}
			}
		}
		if (ans == -1)
			ans = 0;
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
