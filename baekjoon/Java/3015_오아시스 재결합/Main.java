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
			int height = Integer.parseInt(br.readLine()); // �� Ű
			int sameHeightCnt = 1; // ���� Ű�� ���� ��� ��

			// ���ÿ��� ������ ���� ����� �� ������ ���� ������ʹ� �����ϱ� pop
			while (!Q.isEmpty() && Q.peek()[HEIGHT] <= height) {
				// ���� ��
				ans += Q.peek()[CNT];

				// ���� Ű�� pop �ϴµ�
				// ���Ŀ� �� �� �� �ִ� ����� ���� Ű�� ���� ����� �� �� �����Ƿ�
				// ���ڸ� ���� ����
				if (Q.peek()[HEIGHT] == height)
					sameHeightCnt += Q.peek()[CNT];

				Q.pop();
			}

			// ���ÿ� �����ִ� ������ ū ����� ��
			if (!Q.isEmpty())
				ans++;

			Q.push(new int[] { height, sameHeightCnt });
		}

		System.out.println(ans);
	}

}
