import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int N;
	static ArrayList<Integer> nums = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		for (int idx = 0; idx < N; idx++) {
			nums.add(Integer.parseInt(br.readLine()));
		}

		Collections.sort(nums);

		for (int idx: nums) {
			bw.write(idx + "\n");
		}

		bw.flush();
	}

}