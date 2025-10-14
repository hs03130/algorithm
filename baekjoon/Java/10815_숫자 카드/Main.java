import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < M; idx++) {
			int num = Integer.parseInt(st.nextToken());
			int left = 0, mid = N / 2, right = N - 1;
			boolean flag = false;
			while (left <= right) {
				if (nums[mid] == num) {
					flag = true;
					break;
				} else if (nums[mid] < num) {
					left = mid + 1;
					mid = (left + right) / 2;
				} else {
					right = mid - 1;
					mid = (left + right) / 2;
				}
			}
			if (flag)
				sb.append("1 ");
			else
				sb.append("0 ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}