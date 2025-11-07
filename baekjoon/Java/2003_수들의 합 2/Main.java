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

	static int N, M;
	static int[] nums;
	static int ans;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(st.nextToken());
		}

		int left = 0, right = 1, sum = nums[0];
		while (right <= N) {
			if (sum == M) {
				ans++;
			}
			if (sum <= M) {
				if (right < N) sum += nums[right];
				right++;
			}
			if (sum > M) {
				sum -= nums[left++];
			}
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
