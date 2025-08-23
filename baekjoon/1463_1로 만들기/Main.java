/*
 * �Է¹��� ���� num�� 1�� ����� �ּ� Ƚ��
 * 1. 1����
 * 2. 2���� ������ (�������� 0�� ���)
 * 3. 3���� ������ (�������� 0�� ���)
 * ex) 10 -> 9 -> 3 -> 1
 * 
 * �ݴ�� 1���� num�� �����
 * num�� 1�϶��� 0
 * 
 * 1. 1 ���Ѵ�
 * 2. 2 ���Ѵ� 
 * 3. 3 ���Ѵ�
 * ex) 1 -> 3 -> 9 -> 10
 * 1���� 3�� ���ؼ� 3�� ����� Ƚ���� {1������� Ƚ��}+1��
 * 3���� 3�� ���ؼ� 9�� ����� Ƚ���� {3������� Ƚ��}+1��
 * 9���� 1�� ���ؼ� 10�� ����� Ƚ���� {9�� ����� Ƚ��}+1��
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

	// �Է�
	static void input() throws IOException {
		target = Integer.parseInt(br.readLine().trim());
	}

	// target�� 1�� �����
	static void solve() {
		dp = new int[target + 1];

		// 1���� target �����
		for (int num = 2; num <= target; num++) {
			// 1���ϱ�
			dp[num] = dp[num - 1] + 1;
			// 2�� ���������� : {2���� ������ ����� Ƚ��} + 1(2���ϱ�)
			if (num % 2 == 0)
				dp[num] = Math.min(dp[num], dp[num / 2] + 1);
			// 3���� ���������� : {3���� ������ ����� Ƚ��} + 1(3���ϱ�)
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