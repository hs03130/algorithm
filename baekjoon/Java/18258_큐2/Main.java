import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int cmdCnt;
	static String cmd;
	static int num;
	static Deque<Integer> Q;
	

	public static void main(String[] args) throws IOException {

		cmdCnt = Integer.parseInt(br.readLine());
		Q = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		while(cmdCnt-- > 0) {
			st = new StringTokenizer(br.readLine());
			cmd = st.nextToken();
			
			if (cmd.equals("push")) {
				num = Integer.parseInt(st.nextToken());
				Q.offer(num);
			} else if (cmd.equals("pop")) {
				if (Q.isEmpty()) {
					sb.append("-1\n");
				} else {
					sb.append(Q.pop() + "\n");
				}
			} else if (cmd.equals("size")) {
				sb.append(Q.size() + "\n");
			} else if (cmd.equals("empty")) {
				if (Q.isEmpty()) {
					sb.append("1\n");
				} else {
					sb.append("0\n");
				}
			} else if (cmd.equals("front")) {
				if (Q.isEmpty()) {
					sb.append("-1\n");
				} else {
					sb.append(Q.peek() + "\n");
				}
			} else if (cmd.equals("back")) {
				if (Q.isEmpty()) {
					sb.append("-1\n");
				} else {
					sb.append(Q.peekLast() + "\n");
				}
			}
		}
		
		System.out.println(sb);
	}

}
