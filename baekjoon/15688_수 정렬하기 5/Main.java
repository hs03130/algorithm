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

	// numCnt <= 1_000_000
	// 절대값 1_000_000 이하, 비내림차순 정렬
	static int numCnt;
	static int[] nums;

	public static void main(String[] args) throws IOException {

		numCnt = Integer.parseInt(br.readLine());
		nums = new int[2_000_001];
		for (int idx = 0; idx < numCnt; idx++) {
			nums[Integer.parseInt(br.readLine()) + 1_000_000]++;
		}

		for (int idx = 0; idx < 2_000_001; idx++) {
			while (nums[idx]-- > 0) {
				sb.append(idx - 1_000_000 + "\n");
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}