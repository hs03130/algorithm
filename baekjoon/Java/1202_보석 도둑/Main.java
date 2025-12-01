import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int gemCnt, bagCnt;
	static Gem[] gems;
	static int[] bags;
	static PriorityQueue<Integer> pq;
	static long ans = 0L;

	static class Gem implements Comparable<Gem> {
		int weight, price;

		public Gem(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}

		@Override
		public int compareTo(Gem o) {
			if (this.weight != o.weight)
				return this.weight - o.weight;
			return o.price - this.price;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		gemCnt = Integer.parseInt(st.nextToken());
		bagCnt = Integer.parseInt(st.nextToken());

		gems = new Gem[gemCnt];
		for (int idx = 0; idx < gemCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			gems[idx] = new Gem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(gems); // 무게 오름차순, 가격 내림차순

		bags = new int[bagCnt];
		for (int idx = 0; idx < bagCnt; idx++) {
			bags[idx] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bags); // 무게 오름차순

		pq = new PriorityQueue<>(Collections.reverseOrder()); // 가격 내림차순으로 정렬
		// 작은 가방부터 확인
		for (int bagIdx = 0, gemIdx = 0; bagIdx < bagCnt; bagIdx++) {
			// 가방 용량보다 작은 gem을 pq에 넣는다.
			while (gemIdx < gemCnt && gems[gemIdx].weight <= bags[bagIdx]) {
				pq.add(gems[gemIdx++].price);
			}

			// 가방 용량은 점점 커지므로 pq에 있는 gem은 모두 가방에 넣을 수 있다.
			// 가격이 가장 높은 것부터 pq에서 제거한다.
			if (!pq.isEmpty()) {
				ans += pq.poll();
			}
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
