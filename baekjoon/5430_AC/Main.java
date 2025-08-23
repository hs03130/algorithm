import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int testCase;
	static char[] cmd;
	static int N;
	static int[] nums;

	public static void main(String[] args) throws IOException {

		testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			cmd = br.readLine().toCharArray();
			N = Integer.parseInt(br.readLine());
			nums = new int[N];

			String numStr = br.readLine().replace("[", "").replace("]", "");
			String[] tokens = numStr.split(",");
			for (int idx = 0; idx < N; idx++) {
				nums[idx] = Integer.parseInt(tokens[idx]);
			}

			StringBuilder sb = new StringBuilder();

			boolean isFront = true;
			int front = 0, back = N - 1;
			for (int idx = 0; idx < cmd.length; idx++) {
				// 뒤집기
				if (cmd[idx] == 'R') {
					int tmp = front;
					front = back;
					back = tmp;

					isFront = !isFront;
				}
				// 첫번째 수 버리기
				else { // cmd[idx] == 'D'
					if (N <= 0) {
						sb.append("error");
						break;
					}

					if (isFront) {
						front++;
					} else {
						front--;
					}
					N--;
				}

				// 모든 연산 수행 완료
				if (idx == cmd.length - 1) {
					sb.append("[");

					if (isFront) {
						for (int index = front; index <= back; index++) {
							sb.append(nums[index] + ",");
						}
					} else {
						for (int index = front; index >= back; index--) {
							sb.append(nums[index] + ",");
						}
					}

					// 마지막 "," 빼기
					if (sb.charAt(sb.length() - 1) == ',')
						sb.deleteCharAt(sb.length() - 1);

					sb.append("]");
				}
			}

			System.out.println(sb);
		}

	}

}
