import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int numCnt;
	static int[] nums;
	static ArrayList<Integer> sums;

	public static void main(String[] args) throws IOException {
		numCnt = Integer.parseInt(br.readLine());
		nums = new int[numCnt];
		for (int idx = 0; idx < numCnt; idx++) {
			nums[idx] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(nums);

		sums = new ArrayList<>();
		for (int x = 0; x < numCnt; x++) {
			for (int y = x; y < numCnt; y++) { // x + x + x = k도 가능
				sums.add(nums[x] + nums[y]);
			}
		}
		Collections.sort(sums);

		// x + y + z = k인 가장 큰 k를 찾기
		// sums : x + y
		// k - z = x + y
		for (int k = numCnt - 1; k >= 1; k--) { // 자연수 이므로 (x+y) + z = z인 경우는 없다
			for (int z = 0; z < k; z++) {
				int left = -1, right = sums.size();
				while (left + 1 < right) {
					int mid = (left + right) / 2;
					if (sums.get(mid) < nums[k] - nums[z]) {
						left = mid;
					} else {
						right = mid;
					}
				}
				if (right < sums.size() && sums.get(right) == nums[k] - nums[z]) {
					System.out.println(nums[k]);
					return;
				}
			}
		}
	}

}