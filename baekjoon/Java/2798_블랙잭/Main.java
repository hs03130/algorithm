import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int cardCnt, target;
	static int cards[];

	static int ans = 0;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		cardCnt = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());

		cards = new int[cardCnt];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < cardCnt; idx++) {
			cards[idx] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < cardCnt - 2; i++) {
			for (int j = i + 1; j < cardCnt - 1; j++) {
				for (int k = j + 1; k < cardCnt; k++) {
					int sum = cards[i] + cards[j] + cards[k];
					if (sum <= target && sum > ans)
						ans = sum;
				}
			}
		}

		System.out.println(ans);
	}

}
