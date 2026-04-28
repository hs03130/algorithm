import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static String line;

	static int place[] = new int[26];

	public static void main(String[] args) throws IOException {

		line = br.readLine();

		Arrays.fill(place, -1);

		for (int index = 0; index < line.length(); index++) {
			int c = line.charAt(index) - 'a';
			if (place[c] == -1) {
				place[c] = index;
			}
		}

		for (int index = 0; index < 26; index++) {
			System.out.print(place[index] + " ");
		}
	}

}
