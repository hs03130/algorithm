/*
1. �Է�
1-1 �Է� ���� ���� ����
1-2 �׽�Ʈ Ƚ��
1-3 ���� ���
���� ����� �Է��� ���� ��, ù��° ���ں��� ���� ���ڱ����� ���� �����Ѵ�
numbers�迭���� ���� ���ڱ����� ���� ã�� �̹��� �Է� ���� ���ڸ� ���ؼ� numbers�� ����
�迭�� ���� �� numbers ũ��� +1 ���ش�. -> ���ڿ� ���� �ε����� 1������ ���
	-> �� �׽�Ʈ���� �ε����� 0~N-1�� �ƴ� 1~N ���� �Էµ� (numbers[idx]�� ����)
	-> ���� ���� ���ڰ� ù��° �����϶� numbers[-1]�� ���� ó������ �ʾƵ� ��

2. �׽�Ʈ
2-1 �׽�Ʈ Ƚ����ŭ ���� �Է� ����
2-2 ������ �ش��ϴ� ���ڵ��� �� ���ϱ�
	A, B ���ڰ� �ԷµǸ� (A��° ���� ����)~(B��° ���� ����) ������ ����
	B��° ���ڱ����� ������ - (A-1)��° ���ڱ����� �������̴�.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int numberCnt;
	static int testCnt;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		// �Է��� �����鼭 ������ ���
		input();
		
		// �� ������ �� ���
		solve();
	}

	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		numberCnt = Integer.parseInt(st.nextToken());	// ���� ���� �Է�
		testCnt = Integer.parseInt(st.nextToken());	// �׽�Ʈ Ƚ�� �Է�

		// 0�� �ε����� ���� �ּ� 1��° ���ڿ� ���� ����� ���ϰ� �ϰ�,
		// �� �׽�Ʈ���� �ԷµǴ� �ε����� �״�� ����� �� ����
		numbers = new int[numberCnt + 1];	// �������� ������ �迭 ����


		// ���ڸ� �Է� �����鼭 ������ ���
		// ������ : ���� ���ڵ��� �� + ���� �Է� ���� ����
		// ���� ���ڵ��� ���� numbers[idx-1]�� �ٷ� Ȯ���� �� �ִ�.
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 1; idx <= numberCnt; idx++) {
			int num = Integer.parseInt(st.nextToken());
			numbers[idx] = numbers[idx - 1] + num;
		}
	}
	
	// �� �׽�Ʈ���� �־����� ������ �� ���
	// ������ ���� - (����-1) ����
	static void solve() throws IOException {
		for (int cnt = 0; cnt < testCnt; cnt++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			int left = Integer.parseInt(st.nextToken());	// ���� ����
			int right = Integer.parseInt(st.nextToken());	// ������ ����
			System.out.println(numbers[right] - numbers[left-1]);	// ������ ���, ���
		}
	}

}