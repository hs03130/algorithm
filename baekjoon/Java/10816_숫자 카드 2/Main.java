import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static final int RANGE = 10_000_000;

	static int N, M;
	static int[] cnt = new int[2 * RANGE + 1];

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			int num = Integer.parseInt(st.nextToken());
			cnt[num + RANGE]++;
		}

		M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int idx = 0; idx < M; idx++) {
			int num = Integer.parseInt(st.nextToken());
			sb.append(cnt[num + RANGE] + " ");
		}
		
		System.out.println(sb);
	}

}