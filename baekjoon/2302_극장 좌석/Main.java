import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static int totalCnt, fixCnt;
	static int[] fixSeats;
	static int[] dp;
	static int ans = 1;

	public static void main(String[] args) throws IOException {
		// ����� �� ���
		dp = new int[41];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		for (int idx = 3; idx <= 40; idx++) {
			dp[idx] = dp[idx - 1] + dp[idx - 2];
		}

		// �Է�
		totalCnt = Integer.parseInt(br.readLine());
		fixCnt = Integer.parseInt(br.readLine());
		fixSeats = new int[fixCnt + 1];
		for (int idx = 0; idx < fixCnt; idx++) {
			fixSeats[idx] = Integer.parseInt(br.readLine());
		}
		fixSeats[fixCnt] = totalCnt + 1;

		// ������ ����Ͽ� ����� �� ���
		int tmp = 0;
		for (int fixIdx = 0; fixIdx <= fixCnt; fixIdx++) {
			int fix = fixSeats[fixIdx];
			ans *= dp[fix - tmp - 1];
			tmp = fix;
		}

		/*
		 fixSeats -> {0, ���� ������ ... , totalCnt + 1}�� �ϸ�
		 for(int idx = 1; idx <= fixCnt; fixIdx++) {
		 	ans *= dp[fixSeats[idx] - fixSeats[idx-1] - 1];
		 }
		 ó�� �ڵ带 ª�� �ۼ� ���������� �������� ���� ���� ���� ������
		 */
		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}