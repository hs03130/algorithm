import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int testCase;

	static String line;
	static int score;
	static int ans;

	public static void main(String[] args) throws IOException {

		testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < testCase; tc++) {
			line = br.readLine();
			ans = 0;
			score = 0;
			for (int index = 0; index < line.length(); index++) {

				if (line.charAt(index) == 'O') {
					ans += ++score;
				} else {
					score = 0;
				}
			}
			
			System.out.println(ans);
		}

	}

}
