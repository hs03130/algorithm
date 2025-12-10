import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int boardSize;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		boardSize = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int row = 0; row < boardSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < boardSize; col++) {
				pq.add(Integer.parseInt(st.nextToken()));
			}
		}

		for (int idx = 1; idx < boardSize; idx++) {
			pq.poll();
		}

		sb.append(pq.poll());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
