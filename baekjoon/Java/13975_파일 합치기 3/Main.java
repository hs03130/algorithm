import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int testCnt, fileCnt;
	static PriorityQueue<Long> files;
	static long ans;

	public static void main(String[] args) throws IOException {
		testCnt = Integer.parseInt(br.readLine());
		while (testCnt-- > 0) {
			ans = 0L;
			fileCnt = Integer.parseInt(br.readLine());

			files = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			for (int idx = 0; idx < fileCnt; idx++) {
				files.add(Long.parseLong(st.nextToken()));
			}

			while (true) {
				Long file1 = files.poll();
				Long file2 = files.poll();

				if (file1 != null && file2 != null) {
					ans += file1 + file2;
					files.add(file1 + file2);
				} else {
					break;
				}
			}

			sb.append(ans + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
