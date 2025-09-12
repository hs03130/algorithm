
/*
 * 괄호를 적절히 쳐서 식의 값을 최소로 만든다.
 * -> 빼기 뒤의 덧셈 괄호를 묶어준다.
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
		// 입력
		exp = br.readLine();

		// 풀이
		// 숫자와 연산자 파싱
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
		
		// 출력
		System.out.println(ans);
	}

}