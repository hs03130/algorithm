import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BinarySearch {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int cntA, cntB;
	static int[] numsA, numsB;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		cntA = Integer.parseInt(st.nextToken());
		cntB = Integer.parseInt(st.nextToken());

		numsA = new int[cntA];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < cntA; idx++) {
			numsA[idx] = Integer.parseInt(st.nextToken());
		}

		numsB = new int[cntB + 1];
		numsB[0] = 0;
		st = new StringTokenizer(br.readLine());
		for (int idx = 1; idx <= cntB; idx++) {
			numsB[idx] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numsA);
		Arrays.sort(numsB);

		for (int num : numsA) {
			// check(x) = (x >= num)
			int left = 0, right = cntB;
			while (left + 1 < right) {
				int mid = (left + right) / 2;
				if (numsB[mid] < num)
					left = mid;
				else
					right = mid;
			}
			if (numsB[right] != num) {
				sb.append(num + " ");
				ans++;
			}
		}

		bw.write(ans + "\n");
		if (ans > 0) {
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();
	}

}
