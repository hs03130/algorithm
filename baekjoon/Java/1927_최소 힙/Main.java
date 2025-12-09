import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int cmdCnt; // 연산 개수
	static int num; // [제거|삽입할 수]
	static PriorityQueue<Integer> heap;

	public static void main(String[] args) throws Exception {
		cmdCnt = Integer.parseInt(br.readLine());

		heap = new PriorityQueue<>();
		while (cmdCnt-- > 0) {
			num = Integer.parseInt(br.readLine());
			// 힙에 삽입
			if (num > 0) {
				heap.offer(num);
			}
			// 힙에서 제거
			else {
				// 힙이 비었으면 0 아니면 가장 작은 수 제거해서 출력
				System.out.println(heap.isEmpty() ? 0 : heap.poll());
			}
		}
	}

}