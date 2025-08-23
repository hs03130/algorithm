import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int numCnt, findCnt;
	static int findNum;
	static Deque<Integer> DQ;
	static int ans = 0;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		numCnt = Integer.parseInt(st.nextToken());
		findCnt = Integer.parseInt(st.nextToken());
		
		DQ = new ArrayDeque<>();
		for (int num = 1; num <= numCnt; num++) {
			DQ.offer(num);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < findCnt; idx++) {
			findNum = Integer.parseInt(st.nextToken());
			
			int first = DQ.peek();
			
			// �������� �̵� (�ڷ� ������)
			int cntL = 0;
			while(DQ.peek() != findNum) {
				DQ.offerLast(DQ.pollFirst());
				cntL++;
			}
			
			// �ٽ� ��������
			while(DQ.peek() != first) {
				DQ.offerLast(DQ.pollFirst());
			}
			
			// ���������� �̵� (������ ������)
			int cntR = 0;
			while(DQ.peek() != findNum) {
				DQ.offerFirst(DQ.pollLast());
				cntR++;
			}
			
			ans += Math.min(cntL, cntR);
			DQ.pollFirst();
		}

		System.out.println(ans);
	}

}
