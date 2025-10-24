import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int T, N, M;
	static int[] arrA, arrB;
	static long[] dpA, dpB;
	static ArrayList<Long> listA, listB;
	static long ans = 0;

	static int lowerBound(long num) {
		int left = -1, right = listB.size();
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (listB.get(mid) < num) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return right;
	}

	static int upperBound(long num) {
		int left = -1, right = listB.size();
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (listB.get(mid) <= num) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return right;
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());

		N = Integer.parseInt(br.readLine());
		arrA = new int[N + 1]; // 1-based
		dpA = new long[N + 1]; // 1~idx 까지의 합
		st = new StringTokenizer(br.readLine());
		for (int idx = 1; idx <= N; idx++) {
			arrA[idx] = Integer.parseInt(st.nextToken());
			dpA[idx] = dpA[idx - 1] + arrA[idx];
		}

		M = Integer.parseInt(br.readLine());
		arrB = new int[M + 1]; // 1-based
		dpB = new long[M + 1]; // 1~idx 까지의 합
		st = new StringTokenizer(br.readLine());
		for (int idx = 1; idx <= M; idx++) {
			arrB[idx] = Integer.parseInt(st.nextToken());
			dpB[idx] = dpB[idx - 1] + arrB[idx];
		}

		// 각각의 부 배열의 합을 구한다 (DP)
		listA = new ArrayList<>();
		for (int row = 1; row <= N; row++) {
			for (int col = 0; col < row; col++) {
				listA.add(dpA[row] - dpA[col]);
			}
		}

		listB = new ArrayList<>();
		for (int row = 1; row <= M; row++) {
			for (int col = 0; col < row; col++) {
				listB.add(dpB[row] - dpB[col]);
			}
		}

		Collections.sort(listB);
		for (long sumA : listA) {
			ans += upperBound(T - sumA) - lowerBound(T - sumA);
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}