import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int placeCnt, queryCnt;
	static TreeSet<Integer> attractions;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		placeCnt = Integer.parseInt(st.nextToken());
		queryCnt = Integer.parseInt(st.nextToken());

		attractions = new TreeSet<>();
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < placeCnt; idx++) {
			int type = Integer.parseInt(st.nextToken());
			if (type == 1) {
				attractions.add(idx);
			}
		}

		int dohyeon = 0;
		while (queryCnt-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if (cmd == 1) {
				int place = Integer.parseInt(st.nextToken()) - 1;
				if (attractions.contains(place)) {
					attractions.remove(place);
				} else {
					attractions.add(place);
				}
			} else if (cmd == 2) {
				dohyeon = (dohyeon + Integer.parseInt(st.nextToken())) % placeCnt;
			} else if (cmd == 3) {
				if (!attractions.isEmpty()) {
					Integer nextAttraction = attractions.ceiling(dohyeon);
					if (nextAttraction != null) {
						sb.append((nextAttraction - dohyeon + placeCnt) % placeCnt + "\n");
					} else {
						sb.append((attractions.first() - dohyeon + placeCnt) % placeCnt + "\n");
					}
				} else {
					sb.append("-1 \n");
				}
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
