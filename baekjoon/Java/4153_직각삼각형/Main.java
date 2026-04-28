import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static Vector<Integer> nums;

	public static void main(String[] args) throws IOException {

		String line = br.readLine();

		while (!line.equals("0 0 0")) {
			st = new StringTokenizer(line);

			nums = new Vector<>();
			for (int index = 0; index < 3; index++) {
				nums.add(Integer.parseInt(st.nextToken()));
			}

			Collections.sort(nums);

			if (Math.pow(nums.get(0), 2) + Math.pow(nums.get(1), 2) == Math.pow(nums.get(2), 2)) {
				System.out.println("right");
			} else {
				System.out.println("wrong");
			}

			line = br.readLine();
		}

	}

}
