import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static final String FB[] = { "Fizz", "Buzz", "FizzBuzz" };

	static String line[] = new String[3];
	
	static int index;
	static int ans;

	public static void main(String[] args) throws IOException {

		for (int idx = 0; idx < 3; idx++) {
			line[idx] = br.readLine();

			// 숫자 위치 찾기
			boolean flag = false;
			for (String fb : FB) {
				if (fb.equals(line[idx])) flag = true;
			}
			// Fizz, Buzz, FizzBuzz 가 아님
			if (!flag) {
				index = idx;
			}
		}
		
		ans = Integer.parseInt(line[index]);
		ans += 3 - index;
		
		
		if (ans % 3 == 0 && ans % 5 == 0) {
			System.out.println("FizzBuzz");
		} else if (ans % 3 == 0 && ans % 5 != 0) {
			System.out.println("Fizz");
		} else if (ans % 3 != 0 && ans % 5 == 0) {
			System.out.println("Buzz");
		} else {
			System.out.println(ans);
		}

	}

}
