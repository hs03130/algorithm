import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int testCase;

	public static void main(String[] args) throws IOException {

		testCase = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < testCase; tc++) {
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());

			int repeatCnt = Integer.parseInt(st.nextToken());
			
			String word = st.nextToken();
			
			for (int index = 0; index < word.length(); index++) {
				char c = word.charAt(index);
				
				for (int re = 0; re < repeatCnt; re++) {
					sb.append(c);
				}
			}
			
			System.out.println(sb);
		}
	}

}
