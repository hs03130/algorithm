import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int testCase;
	static int hotelH, hotelW, guest;
	static int Y, X;

	public static void main(String[] args) throws IOException {

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			hotelH = Integer.parseInt(st.nextToken());
			hotelW = Integer.parseInt(st.nextToken());
			guest = Integer.parseInt(st.nextToken());

			Y = guest % hotelH;

			if (Y == 0) {
				Y = hotelH;
				X = guest / hotelH;
			} else {
				X = guest / hotelH + 1;
			}

			String ansX = String.format("%02d", X);

			System.out.println(Y + ansX);
		}

	}

}
