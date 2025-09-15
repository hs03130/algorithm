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
			// �� �ȿ��� i-L+1 ~ i ������ ���ڵ��� ����
			// ���� ������������ ����
			// ���� ���� ���ڴ� ����

			arr[idx] = Integer.parseInt(st.nextToken());
			// arr[idx]���� ū ���ڵ��� ����
			while (!DQ.isEmpty() && arr[DQ.getLast()] > arr[idx]) {
				DQ.removeLast();
			}

			// i-L+1 ���� ���� ��ġ�� ����
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
