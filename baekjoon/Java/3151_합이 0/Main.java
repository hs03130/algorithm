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

	static int studentCnt;
	static int[] levels;
	static long ans = 0;

	static int lowerBound(int left, int num) {
		int right = studentCnt;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (levels[mid] < num) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return right;
	}

	static int upperBound(int left, int num) {
		int right = studentCnt;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (levels[mid] <= num) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return right;
	}

	public static void main(String[] args) throws IOException {
		studentCnt = Integer.parseInt(br.readLine());
		levels = new int[studentCnt];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < studentCnt; idx++) {
			levels[idx] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(levels);

		for (int row = 0; row < studentCnt - 1; row++) {
			for (int col = row + 1; col < studentCnt; col++) {
				int lb = lowerBound(col, -levels[row] - levels[col]);
				int ub = upperBound(col, -levels[row] - levels[col]);
				ans += (ub - lb);
			}
		}
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}