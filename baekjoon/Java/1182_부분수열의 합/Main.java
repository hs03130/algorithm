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

	static int numCnt, target;
	static int ans = 0;
	static int[] nums;

	static void bt(int k, int sum) {
		if (k == numCnt) {
			if (sum == target) {
				ans++;
			}
			return;
		}

		// k ����
		bt(k + 1, sum + nums[k]);

		// k ������
		bt(k + 1, sum);
	}

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		numCnt = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		nums = new int[numCnt];

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < numCnt; idx++) {
			nums[idx] = Integer.parseInt(st.nextToken());
		}

		bt(0, 0);
		
		// sum�� 0���� �����ϹǷ� target�� 0�϶� ��� ������ �� ��� ����
		if(target == 0) {
			ans--;
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
