/*
 * 1. �Է�
 *  1-1 ��ȣ���� | �Էµ� ���� ����
 *  1-2 ����
 * 2. ���� �����ؼ� ��ȣ �����
 * 3. ����/���� ���� ���� ������ ��ȣ���� Ȯ�� -> �����ϸ� ���
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb;

	static int selectCnt;
	static int elementCnt;
	static int[] selectList;
	static char[] elementList;

	// �Է�
	static void input() throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		selectCnt = Integer.parseInt(st.nextToken());
		elementCnt = Integer.parseInt(st.nextToken());

		selectList = new int[selectCnt];
		elementList = new char[elementCnt];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < elementCnt; idx++) {
			elementList[idx] = st.nextToken().charAt(0);
		}
	}

	static void solve() {
		sb = new StringBuilder();
		Arrays.sort(elementList);
		// ���ڷ� ��ȣ ����
		bt(0);
	}

	static void bt(int selectIdx) {
		// ��ȣ �ϼ�
		if (selectIdx == selectCnt) {
			int vowelCnt = 0; // ���� ����
			int consoCnt = 0; // ���� ����
			// �ϼ��� ��ȣ�� ���鼭 ���� ���� ����
			for (int idx = 0; idx < selectCnt; idx++) {
				if (checkVowel(elementList[selectList[idx]])) {
					vowelCnt++;
				} else {
					consoCnt++;
				}
			}
			// ����|���� ���� �����ϸ� �Ұ����� ��ȣ
			if (vowelCnt < 1 || consoCnt < 2) {
				return;
			}
			// ������ ��ȣ : ���
			for (int idx = 0; idx < selectCnt; idx++) {
				sb.append(elementList[selectList[idx]]);
			}
			sb.append("\n");
			return;
		}

		for (int idx = selectIdx == 0 ? 0 : selectList[selectIdx - 1] + 1; idx < elementCnt; idx++) {
			selectList[selectIdx] = idx;
			bt(selectIdx + 1);
		}
	}

	// �����̸� true �����̸� false ��ȯ
	static boolean checkVowel(char ch) {
		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		input();
		solve();
		System.out.println(sb.toString());
	}

}