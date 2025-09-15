import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb;

	static int N, L;
	static int[] arr;
	static ArrayDeque<Integer> DQ;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		arr = new int[N];
		DQ = new ArrayDeque<>();
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		for (int idx = 0; idx < N; idx++) {
			// 덱 안에는 i-L+1 ~ i 범위의 숫자들을 포함
			// 덱은 오름차순으로 정렬
			// 범위 밖의 숫자는 제거

			arr[idx] = Integer.parseInt(st.nextToken());
			// arr[idx]보다 큰 숫자들은 제거
			while (!DQ.isEmpty() && arr[DQ.getLast()] > arr[idx]) {
				DQ.removeLast();
			}

			// i-L+1 보다 작은 위치는 제거
			if (!DQ.isEmpty() && DQ.getFirst() < idx - L + 1) {
				DQ.removeFirst();
			}

			DQ.offer(idx);
			sb.append(arr[DQ.getFirst()] + " ");

		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
