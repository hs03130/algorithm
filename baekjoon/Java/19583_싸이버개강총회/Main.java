import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static String S, E, Q;
	static HashSet<String> set;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		S = st.nextToken();
		E = st.nextToken();
		Q = st.nextToken();

		set = new HashSet<>();
		String chat = null;
		while ((chat = br.readLine()) != null) {
			st = new StringTokenizer(chat);
			String time = st.nextToken();

			if (time.compareTo(S) <= 0) {
				set.add(st.nextToken());
			} else if (E.compareTo(time) <= 0 && time.compareTo(Q) <= 0) {
				if (set.remove(st.nextToken())) {
					ans++;
				}
			}
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
