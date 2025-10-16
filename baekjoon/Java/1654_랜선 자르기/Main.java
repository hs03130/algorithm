import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int lanCnt;
	static int targetLanCnt;
	static int[] lan;
	static int maxLan = 0;

	static long left = 1;
	static long right;

	static long ans = 1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		lanCnt = Integer.parseInt(st.nextToken());
		targetLanCnt = Integer.parseInt(st.nextToken());

		lan = new int[lanCnt];
		for (int index = 0; index < lanCnt; index++) {
			lan[index] = Integer.parseInt(br.readLine());
			if (lan[index] > maxLan) {
				maxLan = lan[index];
			}
		}
		right = maxLan;

		while (left <= right) {
			long mid = (left + right) / 2;
			long count = cut(mid);

			if (count >= targetLanCnt) { // 가능한 길이
				ans = mid;
				left = mid + 1; // 하나 늘려서 다시 확인
			} else { // 불가능한 길이
				right = mid - 1; // 하나 줄여서 다시 확인
			}
		}
		
		System.out.println(ans);
	}

	static long cut(long len) {
		long sum = 0;
		for (int index = 0; index < lanCnt; index++) {
			sum += lan[index] / len;
		}
		return sum;
	}
}
