import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int person;
	static int shirts[] = new int[6];
	static int T, P;

	static int ansT;

	public static void main(String[] args) throws IOException {

		person = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < 6; index++) {
			shirts[index] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		ansT = 0;
		for (int index = 0; index < 6; index++) {
			if (shirts[index] % T > 0) {
				ansT += shirts[index] / T + 1;
			} else {
				ansT += shirts[index] / T;
			}
		}

		System.out.println(ansT);
		System.out.println((person / P) + " " + (person % P));
	}

}
