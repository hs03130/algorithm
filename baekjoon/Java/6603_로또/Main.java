import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static String line;
	static int N;
	static int[] element, selected;

	static final int M = 6;

	static void bt(int k, int selectCnt) {
		if (selectCnt == M) {
			for (int idx = 0; idx < M; idx++) {
				sb.append(selected[idx] + " ");
			}
			sb.append("\n");
			return;
		}

		if (k == N) {
			return;
		}

		// 포함
		selected[selectCnt] = element[k];
		bt(k + 1, selectCnt + 1);

		// 미포함
		bt(k + 1, selectCnt);
	}

	public static void main(String[] args) throws IOException {

		line = br.readLine();
		while (!line.equals("0")) {
			st = new StringTokenizer(line);
			N = Integer.parseInt(st.nextToken());

			element = new int[N];
			selected = new int[M];
			for (int idx = 0; idx < N; idx++) {
				element[idx] = Integer.parseInt(st.nextToken());
			}

			bt(0, 0);
			
			sb.append("\n");
			
			line = br.readLine();
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
