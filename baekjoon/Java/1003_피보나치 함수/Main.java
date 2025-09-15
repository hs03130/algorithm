import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int T;
	static int number;
	static int[][] fibo;

	public static void main(String[] args) throws Exception {
		fibo = new int[41][2];
		fibo[0][0] = 1;
		fibo[0][1] = 0;
		fibo[1][0] = 0;
		fibo[1][1] = 1;

		for (int num = 2; num <= 40; num++) {
			fibo[num][0] = fibo[num - 1][0] + fibo[num - 2][0];
			fibo[num][1] = fibo[num - 1][1] + fibo[num - 2][1];
		}

		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			number = Integer.parseInt(br.readLine());
			System.out.println(fibo[number][0] + " " + fibo[number][1]);
		}
	}

}