import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int A, B, C;
	static long ans;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		ans = pow((long) A, B);

		System.out.println(ans);
	}

	static long pow(long num, int cnt) {
		if (cnt == 1)
			return num % C;
		
		long value = pow(num, cnt / 2);
		value = value * value % C;

		if (cnt % 2 == 0)
			return value;
		else
			return value * A % C;
	}
}
