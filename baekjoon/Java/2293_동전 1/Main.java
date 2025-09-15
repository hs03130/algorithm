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

	static int coinCnt, targetPrice;
	static int[] coins;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		coinCnt = Integer.parseInt(st.nextToken());
		targetPrice = Integer.parseInt(st.nextToken());

		coins = new int[coinCnt];
		for (int coin = 0; coin < coinCnt; coin++) {
			coins[coin] = Integer.parseInt(br.readLine());
		}

		dp = new int[targetPrice + 1];
		dp[0] = 1;

		for (int coin : coins) {
			for (int price = coin; price <= targetPrice; price++) {
				dp[price] += dp[price - coin];
			}
		}

		sb.append(dp[targetPrice]);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}