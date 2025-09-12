
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[][] meet;
	static int time = -1;
	static int ans = 0;

	static final int START = 0, END = 1;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		meet = new int[N][2];
		for (int idx = 0; idx < N; idx++) {
			st = new StringTokenizer(br.readLine());
			meet[idx][START] = Integer.parseInt(st.nextToken());
			meet[idx][END] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(meet, (a, b) -> {
			if (a[END] == b[END])
				return Integer.compare(a[START], b[START]);
			return Integer.compare(a[END], b[END]);
		});

		for (int idx = 0; idx < N; idx++) {
			if (meet[idx][START] >= time) {
				ans++;
				time = meet[idx][END];
			}
		}

		System.out.println(ans);
	}

}
