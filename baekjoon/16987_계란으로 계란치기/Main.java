import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int eggCnt;
	static int[][] eggs;
	static int cnt, ans;

	static final int LIFE = 0, WEIGHT = 1;

	static void bt(int k) { // k��° ����� �տ� ���
		if (k == eggCnt) {
			ans = Math.max(ans, cnt);
			return;
		}

		if (eggs[k][LIFE] <= 0 || cnt >= eggCnt - 1) { // �տ� �� ����� �����ų� ������ ���� �ٸ� ����� ������ ġ�� �ʰ� �Ѿ��.
			bt(k + 1);
			return;
		}

		for (int idx = 0; idx < eggCnt; idx++) { // idx��° ��� ����ġ��
			if (idx == k || eggs[idx][LIFE] <= 0) // �տ� �� ����̰ų� �̹� ��������
				continue;

			eggs[k][LIFE] -= eggs[idx][WEIGHT];
			eggs[idx][LIFE] -= eggs[k][WEIGHT];

			if (eggs[k][LIFE] <= 0) cnt++;
			if (eggs[idx][LIFE] <= 0) cnt++;


			bt(k + 1);

			// ����
			if (eggs[k][LIFE] <= 0) cnt--;
			if (eggs[idx][LIFE] <= 0) cnt--;

			eggs[k][LIFE] += eggs[idx][WEIGHT];
			eggs[idx][LIFE] += eggs[k][WEIGHT];
		}

	}

	public static void main(String[] args) throws IOException {

		eggCnt = Integer.parseInt(br.readLine());
		eggs = new int[eggCnt][2];

		for (int idx = 0; idx < eggCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			eggs[idx][LIFE] = Integer.parseInt(st.nextToken());
			eggs[idx][WEIGHT] = Integer.parseInt(st.nextToken());
		}

		ans = 0;
		cnt = 0;

		bt(0);
		
		System.out.println(ans);
	}

}
