import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static int cnt;
	static ArrayList<Long> nums;

	public static void main(String[] args) throws IOException {
		cnt = Integer.parseInt(sc.next());
		nums = new ArrayList<>();

		for (int idx = 0; idx < cnt; idx++) {
			String origin = sc.next();
			String changed = "";
			for (int i = origin.length() - 1; i >= 0; i--) {
				changed += origin.charAt(i);
			}
			nums.add(Long.parseLong(changed));
		}

		Collections.sort(nums);

		for (long num : nums) {
			sb.append(num + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}