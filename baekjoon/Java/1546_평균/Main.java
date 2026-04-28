import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int scores[];
	static int maxScore = 0;
	static int sum = 0;
	static double ans;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		scores = new int[N];
		for (int idx = 0; idx < N; idx++) {
			scores[idx] = Integer.parseInt(st.nextToken());
			
			if (scores[idx] > maxScore)
				maxScore = scores[idx];
			
			sum += scores[idx];
		}

		ans = (sum * 10000) / maxScore / N;

		System.out.println(ans / 100);

	}

}
