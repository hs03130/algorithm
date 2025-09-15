
/*
 * ��ȣ�� ������ �ļ� ���� ���� �ּҷ� �����.
 * -> ���� ���� ���� ��ȣ�� �����ش�.
 * 
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static String exp;
	static StringBuilder copyExp;
	static int operatorCnt = 0;
	static int firstMinusIdx = -1;
	static String[] operandList;
	static int ans;

	public static void main(String[] args) throws Exception {
		// �Է�
		exp = br.readLine();

		// Ǯ��
		// ���ڿ� ������ �Ľ�
		copyExp = new StringBuilder();
		for (int idx = 0; idx < exp.length(); idx++) {
			char ch = exp.charAt(idx);
			if (ch == '+') {
				operatorCnt++;
				copyExp.append(" ");
			} else if (ch == '-') {
				operatorCnt++;
				copyExp.append(" ");
				if (firstMinusIdx == -1) {
					firstMinusIdx = operatorCnt;
				}
			} else {
				copyExp.append(ch);
			}
		}
		String[] operandList = copyExp.toString().split(" ");

		ans = 0;
		if (firstMinusIdx == -1) {
			firstMinusIdx = operatorCnt + 1;
		}
		for (int idx = 0; idx < firstMinusIdx; idx++) {
			ans += Integer.parseInt(operandList[idx]);
		}

		for (int idx = firstMinusIdx; idx <= operatorCnt; idx++) {
			ans -= Integer.parseInt(operandList[idx]);
		}
		
		// ���
		System.out.println(ans);
	}

}