import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BinarySearch {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int totalCnt, findCnt;
	static int[] cards;

	static int lowerBound(int num) {
		int left = -1, right = totalCnt;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (cards[mid] < num) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return right;
	}

	static int upperBound(int num) {
		int left = -1, right = totalCnt;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (cards[mid] <= num) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return right;
	}

	public static void main(String[] args) throws IOException {
		totalCnt = Integer.parseInt(br.readLine());
		cards = new int[totalCnt];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < totalCnt; idx++) {
			cards[idx] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cards);

		findCnt = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < findCnt; idx++) {
			int num = Integer.parseInt(st.nextToken());
			sb.append((upperBound(num) - lowerBound(num)) + " ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}