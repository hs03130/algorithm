import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static String str;
	static Deque<Character> stack;
	static int num; // ������ ��
	static int ans;
	
	
	// �Է�
	static void input() throws Exception {
		str = br.readLine();
	}

	static void solve() throws Exception {
		ans = 0;
		stack = new ArrayDeque<>();
		num = 1;
		for(int idx=0; idx<str.length(); idx++) {
			char brackets = str.charAt(idx);
			if (brackets == '(') {
				num *= 2;
				stack.offerLast(brackets);
			} else if (brackets == '[') {
				num *= 3;
				stack.offerLast(brackets);
			} else if (brackets == ')') {
				// �ùٸ��� ���� ��ȣ��
				if (stack.isEmpty() || stack.peekLast() != '(') {
					ans = 0;
					return;
				}
				if (str.charAt(idx-1) == '(') { // ()
					ans += num;
				}
				num /= 2;
				stack.pollLast();
			} else if (brackets == ']') {
				// �ùٸ��� ���� ��ȣ��
				if (stack.isEmpty() || stack.peekLast() != '[') {
					ans = 0;
					return;
				}
				if (str.charAt(idx-1) == '[') { // []
					ans += num;
				}
				num /= 3;
				stack.pollLast();
			}
		}
		// �ùٸ��� ���� ��ȣ��
		if (!stack.isEmpty()) {
			ans = 0;
			return;
		}
	}

	public static void main(String[] args) throws Exception {
		input();
		solve();
		System.out.println(ans);
	}

}