import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Set {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int numCnt;
	static int[] nums;
	static HashSet<Integer> sums;

	public static void main(String[] args) throws IOException {
		numCnt = Integer.parseInt(br.readLine());
		nums = new int[numCnt];
		for (int idx = 0; idx < numCnt; idx++) {
			nums[idx] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(nums);

		sums = new HashSet<>();
		for (int x = 0; x < numCnt; x++) {
			for (int y = x; y < numCnt; y++) {
				sums.add(nums[x] + nums[y]);
			}
		}

		for (int k = numCnt - 1; k >= 1; k--) {
			for (int z = 0; z < k; z++) {
				if (sums.contains(nums[k] - nums[z])) {
					System.out.println(nums[k]);
					return;
				}
			}
		}
	}

}