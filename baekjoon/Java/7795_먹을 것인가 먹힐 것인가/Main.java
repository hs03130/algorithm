import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int testCase;
	static int cntA, cntB;
	static ArrayList<Integer> listA, listB;
	static int ans;

	public static void main(String[] args) throws IOException {
		testCase = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			cntA = Integer.parseInt(st.nextToken());
			cntB = Integer.parseInt(st.nextToken());

			listA = new ArrayList<>();
			listB = new ArrayList<>();

			st = new StringTokenizer(br.readLine());
			for (int idx = 0; idx < cntA; idx++) {
				listA.add(Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			for (int idx = 0; idx < cntB; idx++) {
				listB.add(Integer.parseInt(st.nextToken()));
			}

			Collections.sort(listA);
			Collections.sort(listB);

			ans = 0;
			int idxA = cntA - 1;
			int idxB = cntB - 1;
			while (idxA >= 0 && idxB >= 0) {
				if (listA.get(idxA) <= listB.get(idxB)) {
					idxB--;
				} else {
					ans += idxB + 1;
					idxA--;
				}
			}

			sb.append(ans + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}