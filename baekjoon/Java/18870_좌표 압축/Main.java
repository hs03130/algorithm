/*
 * �̺�Ž�� lower bound
 * 1. ��ǥ ���� �Է�
 * 2. ���� �迭 �Է�, �ӽ� �迭 �Է�
 * 3. �ӽ� �迭 ����
 * 4. �ӽ� �迭���� �ߺ��� �����ؼ� ����Ʈ�� ����
 * 5. ���� �迭�� ���ʴ�� Ȯ���Ͽ� �ڽź��� ���� ��ǥ ���� ã��
 *    ������ ����Ʈ�� �̺� Ž�� (lower bound)
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int elementCnt;
	static int[] originArr; // ���� �迭
	static int[] tmpArr; // ���ĵ� originArr
	static List<Integer> bsList; // �̺� Ž���� �� �ߺ��� ���ŵ� tmpArr
	static StringBuilder ans;

	public static void main(String[] args) throws Exception {
		// �Է�
		elementCnt = Integer.parseInt(br.readLine());

		originArr = new int[elementCnt];
		tmpArr = new int[elementCnt];

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < elementCnt; idx++) {
			originArr[idx] = Integer.parseInt(st.nextToken());
			tmpArr[idx] = originArr[idx];
		}

		// Ǯ��
		ans = new StringBuilder();

		// ���� �迭�� ���ĵ� �ӽ� �迭
		Arrays.sort(tmpArr);

		// ���ĵ� �ӽ� �迭���� �ߺ� ����
		bsList = new ArrayList<>();
		int elementIdx = 0;
		bsList.add(tmpArr[0]);
		for (int idx = 0; idx < elementCnt; idx++) {
			if (bsList.get(elementIdx) != tmpArr[idx]) {
				bsList.add(tmpArr[idx]);
				elementIdx++;
			}
		}

		// �̺� Ž�� (lower bound)
		for (int idx = 0; idx < elementCnt; idx++) {
			ans.append(Collections.binarySearch(bsList, originArr[idx]) + " ");
		}

		// ���
		System.out.println(ans.toString());
	}

}