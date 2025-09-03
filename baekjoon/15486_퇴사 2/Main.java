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
	static int[] time, price;
	static int[] dp;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		time = new int[N];
		price = new int[N];
		for (int idx = 0; idx < N; idx++) {
			st = new StringTokenizer(br.readLine());
			time[idx] = Integer.parseInt(st.nextToken());
			price[idx] = Integer.parseInt(st.nextToken());
		}

		dp = new int[N + 1];
		for (int today = 0; today < N; today++) {
			// 오늘 상담을 하는 경우
			int nextDay = today + time[today];
			if (nextDay <= N) { // 상담 완료 가능
				dp[nextDay] = Math.max(dp[nextDay], dp[today] + price[today]);
			}

			// 오늘 상담을 하지 않는 경우
			dp[today + 1] = Math.max(dp[today + 1], dp[today]);

			if (ans < dp[today]) {
				ans = dp[today];
			}
		}
		if (ans < dp[N]) {
			ans = dp[N];
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}