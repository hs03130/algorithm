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
	static StringBuilder sb = new StringBuilder();

	// numCnt <= 1_000_000, 수는 중복되지 않음
	// 내림차순 정렬
	static int numCnt;
	static ArrayList<Integer> nums;

	public static void main(String[] args) throws IOException {

		numCnt = Integer.parseInt(br.readLine());
		nums = new ArrayList<>();
		for (int idx = 0; idx < numCnt; idx++) {
			nums.add(Integer.parseInt(br.readLine()));
		}

		Collections.sort(nums, Collections.reverseOrder());

		for (int num : nums) {
			sb.append(num + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}