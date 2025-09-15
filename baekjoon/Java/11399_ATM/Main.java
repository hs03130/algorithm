
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int personCnt;
	static int[] time;
	static int[] cache;
	static int ans;

	public static void main(String[] args) throws Exception {
		ans = 0;

		personCnt = Integer.parseInt(br.readLine());

		time = new int[personCnt + 1];
		cache = new int[personCnt + 1];
		st = new StringTokenizer(br.readLine());
		for (int idx = 1; idx <= personCnt; idx++) {
			time[idx] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(time);

		for (int idx = 1; idx <= personCnt; idx++) {
			cache[idx] = cache[idx - 1] + time[idx];
			ans += cache[idx];
		}

		System.out.println(ans);
	}

}