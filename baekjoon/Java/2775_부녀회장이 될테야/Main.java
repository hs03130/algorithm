import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int apt[][] = new int[15][15];

	static int testCase;
	static int floor, roomNumber;

	public static void main(String[] args) throws IOException {

		for (int idx = 0; idx < 15; idx++) {
			apt[idx][0] = 1;
			apt[0][idx] = idx + 1;
		}

		for (int row = 1; row < 15; row++) {
			for (int col = 1; col < 15; col++) {
				apt[row][col] = apt[row - 1][col] + apt[row][col - 1];
			}
		}

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < testCase; tc++) {
			floor = Integer.parseInt(br.readLine());
			roomNumber = Integer.parseInt(br.readLine()) - 1;

			System.out.println(apt[floor][roomNumber]);
		}

	}

}
