
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int coinCnt;
	static int price;
	static int[] coins;
	static int ans;

	public static void main(String[] args) throws Exception {
		ans = 0;

		st = new StringTokenizer(br.readLine());
		coinCnt = Integer.parseInt(st.nextToken());
		price = Integer.parseInt(st.nextToken());

		coins = new int[coinCnt];
		for (int idx = 0; idx < coinCnt; idx++) {
			coins[idx] = Integer.parseInt(br.readLine());
		}

		for (int idx = (coinCnt - 1); idx >= 0; idx--) {
			if (price == 0) break;
			ans += price/coins[idx];
			price -= coins[idx] * (price/coins[idx]);
		}
		
		System.out.println(ans);
	}

}