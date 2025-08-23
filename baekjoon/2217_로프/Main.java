import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int ropeCnt;
	static int[] ropeWeight;
	static int ans = 0;

	public static void main(String[] args) throws IOException {

		ropeCnt = Integer.parseInt(br.readLine());

		ropeWeight = new int[ropeCnt];
		for (int idx = 0; idx < ropeCnt; idx++) {
			ropeWeight[idx] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(ropeWeight);

		for (int idx = 0; idx < ropeCnt; idx++) {
			int weight = ropeWeight[idx] * (ropeCnt - idx);
			if (weight > ans)
				ans = weight;
		}

		System.out.println(ans);
	}

}
