import java.util.Scanner;

public class Main {

	static int cnt; // ��� Ƚ��

	// ���� ���� : "����Լ��� ������?" ... ��� ž���Ͽ���.
	// ��� �亯 : "�� ����. ...
	// ���� �亯 : "����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����"
	// ���� 0���� cnt-1������ ��� �亯 ���, ������ cnt�� ���� �亯 ��� �� ����
	static void func(int depth, String underScore) {
		System.out.println(underScore + "\"����Լ��� ������?\"");
		
		// ���� ���� : cnt��°���� ���� �亯 ��µǰ� ����
		if (depth == cnt) {
			System.out.println(underScore + "\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"");
			System.out.println(underScore + "��� �亯�Ͽ���.");
			return;
		} 
		
		// ��ͷ� �ݺ� �� �κ�
		System.out.println(underScore + "\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.");
		System.out.println(underScore + "���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.");
		System.out.println(underScore + "���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"");

		// ��� ����
		// ���̰� �����Ҷ����� "____" ���� ����
		func(depth + 1, underScore + "____");
		
		System.out.println(underScore + "��� �亯�Ͽ���.");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		cnt = sc.nextInt();
		System.out.println("��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������.");
		func(0, "");
	}

}