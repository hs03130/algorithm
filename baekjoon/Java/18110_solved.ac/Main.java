import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static int sum = 0;
	static int[] scores;
	static double ans;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		scores = new int[N];
		for (int idx = 0; idx < N; idx++) {
			scores[idx] = Integer.parseInt(br.readLine());
			sum += scores[idx];
		}
		Arrays.sort(scores);

		// 제외할 개수
		int trimCnt = (int) Math.round(N * 0.15);
		// 점수에서 제외하기
		for (int idx = 0; idx < trimCnt; idx++) {
			sum -= scores[idx];
			sum -= scores[N - idx - 1];
		}
		
		ans = (double) sum / (N - (trimCnt * 2));

		System.out.println(Math.round(ans));
	}

}
