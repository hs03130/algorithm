import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static final int ISBN_LEN = 13;

	static String ISBN;
	static int sum = 0;
	static int starIdx;

	static int ans;

	public static void main(String[] args) throws IOException {

		ISBN = br.readLine();

		for (int idx = 0; idx < ISBN_LEN; idx++) {
			if (ISBN.charAt(idx) == '*') {
				starIdx = idx;
			} else {
				int num = Integer.parseInt(String.valueOf(ISBN.charAt(idx)));
				if (idx % 2 == 0) {
					sum += num;
				} else {
					sum += 3 * num;
				}
			}
		}
		
		for (int idx=0; idx<10 ;idx++) {
			if (starIdx % 2 == 0) {
				if ((sum + idx) % 10 == 0) {
					ans = idx;
				}
			} else {
				if  ((sum + 3 * idx) % 10 == 0) {
					ans = idx;
				}
			}
		}

		System.out.println(ans);
	}

}
