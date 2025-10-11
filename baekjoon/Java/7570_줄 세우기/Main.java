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

	static int N;
	static int[] indexes;
	static int ans = 1;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		indexes = new int[N + 1]; // 1-based
		st = new StringTokenizer(br.readLine());
		for (int idx = 1; idx <= N; idx++) {
			int n = Integer.parseInt(st.nextToken());
			indexes[n] = idx;
		}

		int cnt = 1;
		for (int num = 1; num < N; num++) {
			if (indexes[num] < indexes[num + 1]) {
				cnt++;
			} else {
				cnt = 1;
			}
			ans = Math.max(ans, cnt);
		}

		sb.append(N - ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
