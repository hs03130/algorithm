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

	static int numCnt;
	static ArrayList<Long> nums;
	static int maxCnt = 0;
	static long ans = 0;

	public static void main(String[] args) throws IOException {

		numCnt = Integer.parseInt(br.readLine());
		nums = new ArrayList<>();
		for (int idx = 0; idx < numCnt; idx++) {
			nums.add(Long.parseLong(br.readLine()));
		}

		Collections.sort(nums);

		long tmpNum = nums.get(0);
		int tmpCnt = 1;
		for (int idx = 1; idx < numCnt; idx++) {
			if (tmpNum != nums.get(idx)) {
				if (tmpCnt > maxCnt) {
					maxCnt = tmpCnt;
					ans = tmpNum;
				}
				tmpNum = nums.get(idx);
				tmpCnt = 0;
			}
			tmpCnt++;
		}
		if (tmpCnt > maxCnt) {
			maxCnt = tmpCnt;
			ans = nums.get(numCnt - 1);
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}