/*
 * 입력받은 숫자 num을 1로 만드는 최소 횟수
 * 1. 1뺀다
 * 2. 2으로 나눈다 (나머지가 0인 경우)
 * 3. 3으로 나눈다 (나머지가 0인 경우)
 * ex) 10 -> 9 -> 3 -> 1
 * 
 * 반대로 1에서 num을 만들기
 * num이 1일때는 0
 * 
 * 1. 1 더한다
 * 2. 2 곱한다 
 * 3. 3 곱한다
 * ex) 1 -> 3 -> 9 -> 10
 * 1에서 3을 곱해서 3을 만드는 횟수는 {1을만드는 횟수}+1번
 * 3에서 3을 곱해서 9를 만드는 횟수는 {3을만드는 횟수}+1번
 * 9에서 1을 더해서 10을 만드는 횟수는 {9를 만드는 횟수}+1번
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int[] dp;
	static int target;
	static int ans;

	// 입력
	static void input() throws IOException {
		target = Integer.parseInt(br.readLine().trim());
	}

	// target을 1로 만들기
	static void solve() {
		dp = new int[target + 1];

		// 1에서 target 만들기
		for (int num = 2; num <= target; num++) {
			// 1더하기
			dp[num] = dp[num - 1] + 1;
			// 2로 나누어지면 : {2나눈 수까지 만드는 횟수} + 1(2곱하기)
			if (num % 2 == 0)
				dp[num] = Math.min(dp[num], dp[num / 2] + 1);
			// 3으로 나누어지면 : {3나눈 수까지 만드는 횟수} + 1(3곱하기)
			if (num % 3 == 0)
				dp[num] = Math.min(dp[num], dp[num / 3] + 1);
		}
		ans = dp[target];
	}

	public static void main(String args[]) throws IOException {
		input();
		solve();
		System.out.println(ans);
	}

}