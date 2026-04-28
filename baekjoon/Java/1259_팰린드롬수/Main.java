import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	static String num;

	public static void main(String[] args) throws IOException {

		num = br.readLine();

		while (!num.equals("0")) {
			for (int index = 0; index < num.length(); index++) {

				if (num.charAt(index) != num.charAt(num.length() - 1 - index)) {
					System.out.println("no");
					break;
				}

				if (index == num.length() - 1) {
					System.out.println("yes");
				}
			}
			num = br.readLine();
		}
	}

}
