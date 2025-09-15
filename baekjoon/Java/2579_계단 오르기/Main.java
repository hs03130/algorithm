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
				// 직전 칸에서 한칸
				// 직전 칸은 두칸으로 올라온 상태
				cache[idx][0] = cache[idx - 1][1] + stairs[idx];

				// 전전 칸에서 두칸
				// 전전 칸은 어떻게 왔는지 상관 없고, 큰 값을 선택
				cache[idx][1] = Math.max(cache[idx - 2][0], cache[idx - 2][1]) + stairs[idx];
			}
			ans = Math.max(cache[stairCnt - 1][0], cache[stairCnt - 1][1]);
		}
		
		System.out.println(ans);
	}

}