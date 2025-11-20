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

	static int N;
	static int[] snow;
	static int ans;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		snow = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			snow[idx] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(snow);

		ans = Math.abs(snow[3] + snow[0] - snow[2] - snow[1]);
		for (int top1 = 0; top1 < N - 3; top1++) {
			for (int bottom1 = top1 + 3; bottom1 < N; bottom1++) {
				int top2 = top1 + 1;
				int bottom2 = bottom1 - 1;
				while (top2 < bottom2) {
					ans = Math.min(ans, Math.abs(snow[top1] + snow[bottom1] - snow[top2] - snow[bottom2]));
					if (snow[top1] + snow[bottom1] < snow[top2] + snow[bottom2]) {
						bottom2--;
					} else {
						top2++;
					}
				}
			}
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
