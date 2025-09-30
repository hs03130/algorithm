import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int numCnt;
	static int[] nums;
	static int[] operatorCnt; // µ¡¼À, »¬¼À, °ö¼À, ³ª´°¼À

	static int minAns = Integer.MAX_VALUE;
	static int maxAns = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		numCnt = Integer.parseInt(br.readLine());

		nums = new int[numCnt];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < numCnt; idx++) {
			nums[idx] = Integer.parseInt(st.nextToken());
		}

		operatorCnt = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < 4; idx++) {
			operatorCnt[idx] = Integer.parseInt(st.nextToken());
		}

		bt(operatorCnt[0], operatorCnt[1], operatorCnt[2], operatorCnt[3], 0, nums[0]);

		sb.append(maxAns + "\n");
		sb.append(minAns + "\n");

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bt(int addCnt, int subCnt, int multiCnt, int divCnt, int k, int result) {
		if (k == numCnt - 1) {
			if (result < minAns) {
				minAns = result;
			}
			if (result > maxAns) {
				maxAns = result;
			}
			return;
		}

		// µ¡¼À
		if (addCnt > 0) {
			bt(addCnt - 1, subCnt, multiCnt, divCnt, k + 1, result + nums[k + 1]);
		}

		// »¬¼À
		if (subCnt > 0) {
			bt(addCnt, subCnt - 1, multiCnt, divCnt, k + 1, result - nums[k + 1]);
		}

		// °ö¼À
		if (multiCnt > 0) {
			bt(addCnt, subCnt, multiCnt - 1, divCnt, k + 1, result * nums[k + 1]);
		}

		// ³ª´°¼À
		if (divCnt > 0) {
			// À½¼ö¸¦ ³ª´­ ¶§ ¾ç¼ö·Î ¹Ù²ã¼­ °è»êÇÏ°í, °á°ú¸¦ À½¼ö·Î ¹Ù²Û´Ù.
			if (result < 0) {
				bt(addCnt, subCnt, multiCnt, divCnt - 1, k + 1, ((result * -1) / nums[k + 1]) * -1);
			} else {
				bt(addCnt, subCnt, multiCnt, divCnt - 1, k + 1, result / nums[k + 1]);
			}
		}
	}
}
