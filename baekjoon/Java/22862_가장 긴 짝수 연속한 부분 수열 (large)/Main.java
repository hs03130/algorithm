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

	static int N, K;
	static int[] nums;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(st.nextToken());
		}

		int left = 0, right = 1, oddCnt = 0;
		if (nums[0] % 2 == 1) oddCnt++;
		else ans++;

		while (right < N) {
			// 새로운 짝수
			if (nums[right] % 2 == 0) {
				right++;
				ans = Math.max(ans, right - left - oddCnt);
			}
			// 홀수 삭제 (수열 길이는 변화 없음)
			else if (oddCnt < K) {
				oddCnt++;
				right++;
			}
			// 첫 홀수 다음 수부터 다시 계산
			else {
				while (nums[left] % 2 == 0) {
					left++;
				}
				oddCnt--;
				left++;
			}
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
