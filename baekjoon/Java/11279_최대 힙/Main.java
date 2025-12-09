import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb;

	static int cmdCnt;
	static int cmd;
	static PriorityQueue<Integer> PQ;

	public static void main(String[] args) throws Exception {
		// 입력
		cmdCnt = Integer.parseInt(br.readLine());

		// 풀이
		sb = new StringBuilder();
		PQ = new PriorityQueue<>(Collections.reverseOrder());
		for(int cnt=0; cnt<cmdCnt; cnt++) {
			cmd = Integer.parseInt(br.readLine());
			
			if (cmd == 0) {
				if (PQ.isEmpty()) {
					sb.append("0\n");
				} else {
					sb.append(PQ.poll() + "\n");
				}
			} else {
				PQ.offer(cmd);
			}
		}
		
		// 출력
		System.out.println(sb.toString());
	}
}