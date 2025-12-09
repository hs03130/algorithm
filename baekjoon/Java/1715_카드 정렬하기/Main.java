import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static int cardCnt;
	static PriorityQueue<Integer> cards;
	static long ans = 0L;

	public static void main(String[] args) throws IOException {
		cardCnt = Integer.parseInt(br.readLine());

		cards = new PriorityQueue<>();
		for (int idx = 0; idx < cardCnt; idx++) {
			cards.add(Integer.parseInt(br.readLine()));
		}

		while (true) {
			Integer card1 = cards.poll();
			Integer card2 = cards.poll();

			if (card1 != null && card2 != null) {
				ans += card1 + card2;
				cards.add(card1 + card2);
			} else {
				break;
			}
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
