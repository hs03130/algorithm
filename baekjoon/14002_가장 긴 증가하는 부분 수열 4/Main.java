import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[] nums;
	static int[][] dp;
	static int ans;

	static final int IDX = 0, CNT = 1;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		dp = new int[N][2];

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(st.nextToken());
		}

		dp[0][IDX] = -1;
		dp[0][CNT] = 1;
		ans = 0;

		for (int n = 1; n < N; n++) {
			dp[n] = new int[] { -1, 1 };
			for (int idx = 0; idx < n; idx++) {
				if (nums[idx] < nums[n] && dp[idx][CNT] >= dp[n][CNT]) {
					dp[n][IDX] = idx;
					dp[n][CNT] = dp[idx][CNT] + 1;
				}
			}
			if (dp[n][CNT] > dp[ans][CNT]) {
				ans = n;
			}
		}

		sb.append(dp[ans][CNT] + "\n");
		
		ArrayList<Integer> list = new ArrayList<>();
		while (ans != -1) {
			list.add(nums[ans]);
			ans = dp[ans][IDX];
		}
		Collections.sort(list);

		for (int num : list) {
			sb.append(num + " ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}