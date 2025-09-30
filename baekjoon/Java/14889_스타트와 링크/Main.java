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

	static int personCnt;
	static int[][] adj;
	static boolean[] selected;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		personCnt = Integer.parseInt(br.readLine());
		adj = new int[personCnt][personCnt];
		for (int row = 0; row < personCnt; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < personCnt; col++) {
				adj[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		selected = new boolean[personCnt];

		// �� �����ϱ�
		bt(0, 0);

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	// �� �����ϱ�
	static void bt(int k, int selectedCnt) {
		// �� ���� �Ϸ�
		if (selectedCnt == personCnt / 2) {
			// �ɷ�ġ ���� Ȯ��
			int startScore = 0;
			int linkScore = 0;
			for (int row = 0; row < personCnt; row++) {
				// ��ŸƮ �� �Ҽ�
				if (selected[row]) {
					for (int col = 0; col < personCnt; col++) {
						if (row == col)
							continue;
						if (selected[col])
							startScore += adj[row][col];
					}
				}
				// ��ũ �� �Ҽ�
				else {
					for (int col = 0; col < personCnt; col++) {
						if (row == col)
							continue;
						if (!selected[col])
							linkScore += adj[row][col];
					}
				}
			}

			int score = Math.abs(startScore - linkScore);
			if (score < ans) {
				ans = score;
			}
			return;
		}

		if (k == personCnt) {
			return;
		}

		// k��° ������ ��ŸƮ ���� ���� ��Ų��
		selected[k] = true;
		bt(k + 1, selectedCnt + 1);
		selected[k] = false;

		// k��° ������ ��ũ ���� ���� ��Ų��
		bt(k + 1, selectedCnt);
	}

}
