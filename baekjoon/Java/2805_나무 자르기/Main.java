import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int treeCnt; // ���� ����
	static int treeLen; // �߶� ���� ����
	static int[] treeList; // ���� ���� ���
	static int maxH; // ���� ū ������ ����
	static int ans;

	public static void main(String[] args) throws Exception {
		// �Է�
		st = new StringTokenizer(br.readLine());
		treeCnt = Integer.parseInt(st.nextToken());
		treeLen = Integer.parseInt(st.nextToken());

		treeList = new int[treeCnt];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < treeCnt; idx++) {
			treeList[idx] = Integer.parseInt(st.nextToken());
			maxH = Math.max(treeList[idx], maxH);
		}

		// Ǯ��
		ans = -1;

		// ���ܱ� ���̴� 0~maxH-1 ���̷� ������ �� �ִ�.
		int left = 0;
		int right = maxH;

		while (left + 1 < right) {
			int mid = (right + left) / 2;

			// ������ ����
			if (check(mid)) {
				left = mid;
			}
			// ������
			else {
				right = mid;
			}
		}
		ans = left;
		
		// ���
		System.out.println(ans);
	}

	static boolean check(int cutH) {
		long sum = 0;
		for(int idx=0; idx<treeCnt; idx++) {
			if (treeList[idx] > cutH) {
				sum += treeList[idx] - cutH;
			}
		}
		return sum >= treeLen;
	}
}