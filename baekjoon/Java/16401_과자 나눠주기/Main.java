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

	static int personCnt, snackCnt;
	static int[] snacks;
	static int ans = 0;

	static final int MAX_LEN = 1000_000_000;

	static boolean check(int len) {
		long sum = 0;
		for (int idx = 0; idx < snackCnt; idx++) {
			sum += snacks[idx] / len;
			if (sum >= personCnt)
				return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		personCnt = Integer.parseInt(st.nextToken());
		snackCnt = Integer.parseInt(st.nextToken());

		snacks = new int[snackCnt];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < snackCnt; idx++) {
			snacks[idx] = Integer.parseInt(st.nextToken());
		}

		int left = 0, right = MAX_LEN;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (check(mid))
				left = mid;
			else
				right = mid;
		}
		sb.append(left);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}