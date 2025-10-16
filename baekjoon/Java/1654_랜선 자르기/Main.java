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

			if (count >= targetLanCnt) { // ������ ����
				ans = mid;
				left = mid + 1; // �ϳ� �÷��� �ٽ� Ȯ��
			} else { // �Ұ����� ����
				right = mid - 1; // �ϳ� �ٿ��� �ٽ� Ȯ��
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
