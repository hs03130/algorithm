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
	static Deque<Integer> DQ;
	

	public static void main(String[] args) throws IOException {

		cmdCnt = Integer.parseInt(br.readLine());
		DQ = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		while(cmdCnt-- > 0) {
			st = new StringTokenizer(br.readLine());
			cmd = st.nextToken();
			
			if (cmd.equals("push_front")) {
				num = Integer.parseInt(st.nextToken());
				DQ.offerFirst(num);
			} else if (cmd.equals("push_back")) {
				num = Integer.parseInt(st.nextToken());
				DQ.offer(num);
			} else if (cmd.equals("pop_front")) {
				if (DQ.isEmpty()) {
					sb.append("-1\n");
				} else {
					sb.append(DQ.poll() + "\n");
				}
			} else if (cmd.equals("pop_back")) {
				if (DQ.isEmpty()) {
					sb.append("-1\n");
				} else {
					sb.append(DQ.pollLast() + "\n");
				}
			} else if (cmd.equals("size")) {
				sb.append(DQ.size() + "\n");
			} else if (cmd.equals("empty")) {
				if (DQ.isEmpty()) {
					sb.append("1\n");
				} else {
					sb.append("0\n");
				}
			} else if (cmd.equals("front")) {
				if (DQ.isEmpty()) {
					sb.append("-1\n");
				} else {
					sb.append(DQ.peek() + "\n");
				}
			} else if (cmd.equals("back")) {
				if (DQ.isEmpty()) {
					sb.append("-1\n");
				} else {
					sb.append(DQ.peekLast() + "\n");
				}
			}
		}
		
		System.out.println(sb);
	}

}
