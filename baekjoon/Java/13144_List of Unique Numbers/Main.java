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

	static int N;
	static int[] nums;
	static boolean[] isUsed;
	static long ans = 0;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		isUsed = new boolean[100001];

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(st.nextToken());
		}

		int left = 0, right = 1;
		isUsed[nums[0]] = true;
		ans = 0;

		while (left < N && right <= N) {
			if (right < N && !isUsed[nums[right]]) {
				isUsed[nums[right++]] = true;
			} else {
				// nums[left]로 시작하는 부분 수열
				ans += right - left;
				isUsed[nums[left++]] = false;
			}
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
