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

	static int budgetCnt, totalBudget;
	static int[] budgets, dp;

	static int lowerBound(int num) {
		int left = -1, right = budgetCnt;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (budgets[mid] < num) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return right;
	}

	// 상한액을 budget으로 했을 때 예산 내에서 배정이 가능한지 확인
	static boolean check(int budget) {
		// 예산 요청액에서 budget 이상인 요청 찾기
		int largeIdx = lowerBound(budget);
		// 상한액으로 지급한 후 나머지 금액
		int lastBudget = totalBudget - ((budgetCnt - largeIdx) * budget);
		return lastBudget >= dp[largeIdx];
	}

	public static void main(String[] args) throws IOException {
		budgetCnt = Integer.parseInt(br.readLine());
		budgets = new int[budgetCnt];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < budgetCnt; idx++) {
			budgets[idx] = Integer.parseInt(st.nextToken());
		}
		totalBudget = Integer.parseInt(br.readLine());

		// dp[idx] : budget[idx] 보다 작은 요청 예산 합
		Arrays.sort(budgets);
		dp = new int[budgetCnt + 1];
		dp[1] = budgets[0];
		for (int idx = 2; idx <= budgetCnt; idx++) {
			dp[idx] = dp[idx - 1] + budgets[idx - 1];
		}

		int left = 1, right = budgets[budgetCnt - 1] + 1;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (check(mid)) {
				left = mid;
			} else {
				right = mid;
			}
		}

		sb.append(left);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
