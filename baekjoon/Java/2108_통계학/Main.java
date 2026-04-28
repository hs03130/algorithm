import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, sum = 0;
	static int[] nums, frequency;

	static final int VALUE = 4000;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		nums = new int[N];
		frequency = new int[2 * VALUE + 1];
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(br.readLine());
			frequency[nums[idx] + VALUE]++;
			sum += nums[idx];
		}
		Arrays.sort(nums);

		// 산술평균
		System.out.println((int) Math.round((double) sum / N));

		// 중앙값
		System.out.println(nums[N / 2]);

		// 최빈값
		int maxFrequency = Integer.MIN_VALUE;
		int mostFrequentNum = 0;
		int secondFrequentNum = 0;
		for (int idx = 0; idx <= VALUE * 2; idx++) {
			if (frequency[idx] > maxFrequency) {
				maxFrequency = frequency[idx];
				mostFrequentNum = idx - VALUE;
			} else if (frequency[idx] == maxFrequency && frequency[idx] != frequency[secondFrequentNum + VALUE]) {
				secondFrequentNum = idx - VALUE;
			}
		}
		System.out.println(frequency[mostFrequentNum + VALUE] == frequency[secondFrequentNum+ VALUE] ? secondFrequentNum : mostFrequentNum);

		// 최대값 - 최소값
		System.out.println(nums[N - 1] - nums[0]);
	}

}
