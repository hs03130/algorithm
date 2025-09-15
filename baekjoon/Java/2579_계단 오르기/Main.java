import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int T;
	static int stairCnt;
	static int[] stairs;
	static int[][] cache;
	static int ans;

	public static void main(String[] args) throws Exception {
		ans = 0;
		
		stairCnt = Integer.parseInt(br.readLine());

		stairs = new int[stairCnt];
		for (int idx = 0; idx < stairCnt; idx++) {
			stairs[idx] = Integer.parseInt(br.readLine());
		}

		if (stairCnt == 1) {
			ans = stairs[0];
		} else if (stairCnt == 2) {
			ans = stairs[0] + stairs[1];
		} else {
			cache = new int[stairCnt][2];
			cache[0][0] = stairs[0];
			cache[1][0] = stairs[0] + stairs[1];
			cache[1][1] = stairs[1];
			
			for (int idx = 2; idx < stairCnt; idx++) {
				// ���� ĭ���� ��ĭ
				// ���� ĭ�� ��ĭ���� �ö�� ����
				cache[idx][0] = cache[idx - 1][1] + stairs[idx];

				// ���� ĭ���� ��ĭ
				// ���� ĭ�� ��� �Դ��� ��� ����, ū ���� ����
				cache[idx][1] = Math.max(cache[idx - 2][0], cache[idx - 2][1]) + stairs[idx];
			}
			ans = Math.max(cache[stairCnt - 1][0], cache[stairCnt - 1][1]);
		}
		
		System.out.println(ans);
	}

}