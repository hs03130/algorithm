import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int peopleCnt;
	static Deque<int[]> Q;
	static long ans = 0L;

	static final int HEIGHT = 0, CNT = 1;

	public static void main(String[] args) throws IOException {

		peopleCnt = Integer.parseInt(br.readLine());
		Q = new ArrayDeque<>();

		for (int idx = 0; idx < peopleCnt; idx++) {
			int height = Integer.parseInt(br.readLine()); // 내 키
			int sameHeightCnt = 1; // 나랑 키가 같은 사람 수

			// 스택에서 나보다 작은 사람은 나 때문에 다음 사람부터는 못보니까 pop
			while (!Q.isEmpty() && Q.peek()[HEIGHT] <= height) {
				// 나랑 쌍
				ans += Q.peek()[CNT];

				// 같은 키도 pop 하는데
				// 이후에 날 볼 수 있는 사람은 나랑 키가 같은 사람도 볼 수 있으므로
				// 숫자를 따로 세줌
				if (Q.peek()[HEIGHT] == height)
					sameHeightCnt += Q.peek()[CNT];

				Q.pop();
			}

			// 스택에 남아있는 나보다 큰 사람과 쌍
			if (!Q.isEmpty())
				ans++;

			Q.push(new int[] { height, sameHeightCnt });
		}

		System.out.println(ans);
	}

}
