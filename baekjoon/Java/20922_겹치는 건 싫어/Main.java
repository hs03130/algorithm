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

	static int N, K;
	static int[] nums, counts;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nums = new int[N];
		counts = new int[100001];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(st.nextToken());
		}

		int left = 0, right = 1;
		counts[nums[0]]++;
		while (right < N) {
			if (counts[nums[right]] == K) {
				while (nums[left] != nums[right]) {
					counts[nums[left++]]--;
				}
				counts[nums[left++]]--;
			}
			counts[nums[right++]]++;
			ans = Math.max(ans, right - left);
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
