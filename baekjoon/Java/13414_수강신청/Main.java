import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int possibleCnt, waitingCnt;
	static Set<Integer> set;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		possibleCnt = Integer.parseInt(st.nextToken());
		waitingCnt = Integer.parseInt(st.nextToken());

		set = new LinkedHashSet<>();
		for (int idx = 0; idx < waitingCnt; idx++) {
			int studentId = Integer.parseInt(br.readLine());
			if (set.contains(studentId)) {
				set.remove(studentId);
			}
			set.add(studentId);
		}

		int printCnt = 0;
		for (int studentId : set) {
			if (printCnt++ == possibleCnt) {
				break;
			}
			sb.append(String.format("%08d", studentId) + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
