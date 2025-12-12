import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static int N;
	static PriorityQueue<Integer> min, max;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		min = new PriorityQueue<Integer>(Collections.reverseOrder());
		max = new PriorityQueue<Integer>();

		for (int idx = 0; idx < N; idx++) {
			int next = Integer.parseInt(br.readLine());

			// min: n개~n+1개, max: n개 유지
			if (min.size() == max.size()) { // 0-0, 1-1, ...
				if (min.isEmpty() || next < max.peek()) {
					min.add(next);
				} else {
					min.add(max.poll());
					max.add(next);
				}
			} else { // 1-0, 2-1, ...
				if (min.peek() < next) {
					max.add(next);
				} else {
					max.add(min.poll());
					min.add(next);
				}
			}

			sb.append(min.peek() + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
