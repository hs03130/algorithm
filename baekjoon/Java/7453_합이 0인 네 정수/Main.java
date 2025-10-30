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

	static int arrSize;
	static long[] arrA, arrB, arrC, arrD;
	static long[] sumA, sumB;
	static long ans = 0;

	static int lowerBound(long num) {
		int left = -1, right = arrSize * arrSize;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (sumB[mid] < num) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return right;
	}
	static int upperBound(long num) {
		int left = -1, right = arrSize * arrSize;
		while(left + 1 < right) {
			int mid = (left + right) / 2;
			if (sumB[mid] <= num) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return right;
	}

	public static void main(String[] args) throws IOException {
		arrSize = Integer.parseInt(br.readLine());
		arrA = new long[arrSize];
		arrB = new long[arrSize];
		arrC = new long[arrSize];
		arrD = new long[arrSize];

		for (int idx = 0; idx < arrSize; idx++) {
			st = new StringTokenizer(br.readLine());
			arrA[idx] = Long.parseLong(st.nextToken());
			arrB[idx] = Long.parseLong(st.nextToken());
			arrC[idx] = Long.parseLong(st.nextToken());
			arrD[idx] = Long.parseLong(st.nextToken());
		}

		sumA = new long[arrSize * arrSize];
		sumB = new long[arrSize * arrSize];
		int sumIdx = 0;
		for (int row = 0; row < arrSize; row++) {
			for (int col = 0; col < arrSize; col++) {
				sumA[sumIdx] = arrA[row] + arrB[col];
				sumB[sumIdx++] = arrC[row] + arrD[col];
			}
		}
		Arrays.sort(sumA);
		Arrays.sort(sumB);

		for (long sum : sumA) {
			int lb = lowerBound(-sum);
			int ub = upperBound(-sum);
			ans += ub - lb;
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
