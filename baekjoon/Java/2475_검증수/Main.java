import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int num[] = new int[5];

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		double sum = 0;
		for (int index = 0; index < 5; index++) {
			num[index] = Integer.parseInt(st.nextToken());
			sum += Math.pow(num[index], 2);
		}

		System.out.println((int) (sum % 10));
	}

}
