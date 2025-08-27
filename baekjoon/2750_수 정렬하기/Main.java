import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	// numCnt <= 1000, 수는 중복되지 않음
	static int numCnt;
	static int[] nums;

	public static void main(String[] args) throws IOException {

		numCnt = Integer.parseInt(br.readLine());
		nums = new int[numCnt];
		for (int idx = 0; idx < numCnt; idx++) {
			nums[idx] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(nums);

		for (int idx = 0; idx < numCnt; idx++) {
			sb.append(nums[idx] + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}