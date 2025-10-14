import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Hash {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int cntA, cntB;
	static int[] numsA;
	static HashMap<Integer, Boolean> mapB;
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

		mapB = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < cntB; idx++) {
			mapB.put(Integer.parseInt(st.nextToken()), true);
		}

		Arrays.sort(numsA);
		for (int num : numsA) {
			if (!mapB.containsKey(num)) {
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
