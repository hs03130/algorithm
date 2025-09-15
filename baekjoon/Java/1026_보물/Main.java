
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static ArrayList<Integer> A = new ArrayList<>();
	static ArrayList<Integer> B = new ArrayList<>();
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			A.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			B.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(A);
		Collections.sort(B, Collections.reverseOrder());

		for (int idx = 0; idx < N; idx++) {
			ans += (A.get(idx) * B.get(idx));
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}