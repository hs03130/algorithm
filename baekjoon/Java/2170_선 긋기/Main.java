import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int lineCnt;
	static ArrayList<Line> lines;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		lineCnt = Integer.parseInt(br.readLine());
		lines = new ArrayList<>();
		for (int idx = 0; idx < lineCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			lines.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		lines.sort((l1, l2) -> {
			if (l1.from != l2.from)
				return l1.from - l2.from;
			return l2.to - l1.to;
		});

		int start = lines.get(0).from, end = lines.get(0).to;
		for (Line line : lines) {
			if (line.from <= end) {
				end = Math.max(end, line.to);
			} else {
				ans += (end - start);
				start = line.from;
				end = line.to;
			}
		}
		ans += (end - start);

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Line {
		int from, to;

		public Line(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
}
