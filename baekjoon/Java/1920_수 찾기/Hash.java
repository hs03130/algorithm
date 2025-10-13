import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Hash {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M;
	static Set<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			set.add(Integer.parseInt(st.nextToken()));
		}

		M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < M; idx++) {
			if (set.contains(Integer.parseInt(st.nextToken()))) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}

}
