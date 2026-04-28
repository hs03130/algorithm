import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int ansMin = Integer.MAX_VALUE;
	static int ansMax = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < N; index++) {
			int number = Integer.parseInt(st.nextToken());
			
			if (number < ansMin)
				ansMin = number;
			
			if (number > ansMax)
				ansMax = number;
		}

		System.out.println(ansMin + " " + ansMax);
	}

}
