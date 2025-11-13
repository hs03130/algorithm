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

	static int plateCnt, kindOfSushi, k, coupon;
	static int[] plates, eated;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		plateCnt = Integer.parseInt(st.nextToken());
		kindOfSushi = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		coupon = Integer.parseInt(st.nextToken());

		plates = new int[plateCnt + k - 1];
		eated = new int[kindOfSushi + 1];

		for (int idx = 0; idx < plateCnt; idx++) {
			plates[idx] = Integer.parseInt(br.readLine());
		}
		for (int idx = 0; idx < k - 1; idx++) {
			plates[plateCnt + idx] = plates[idx];
		}
		plateCnt += k - 1;

		int left = 0, right = 1, eatCnt = 1;
		eated[plates[0]]++;
		while (right <= plateCnt) {
			// k개 접시 먹음
			if (right - left == k) {
				// 먹은 초밥 종류 세기
				ans = eated[coupon] > 0 ? Math.max(ans, eatCnt) : Math.max(ans, eatCnt + 1);
				// 첫번째 접시 제외
				if (--eated[plates[left++]] == 0)
					eatCnt--;
			}

			if (right == plateCnt)
				break;

			// 다음 접시 먹기
			if (++eated[plates[right++]] == 1)
				eatCnt++;
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
