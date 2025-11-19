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

	static int N, K;
	static int[] line;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		line = new int[1000000];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			for (int col = start; col < end; col++) {
				line[col]++;
			}
		}

		int end = 1;
		int sum = line[0];
		for (int start = 0; start < 1000000; start++) {
			while (end < 1000000 && sum < K) {
				sum += line[end++];
			}
			if (sum == K) {
				sb.append(start + " " + end);
				break;
			}
			sum -= line[start];
		}

		if (sb.length() == 0) {
			sb.append("0 0");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
