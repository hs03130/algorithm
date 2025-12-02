import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int problemCnt, cmdCnt;
	static TreeSet<Problem> problems;
	static HashMap<Integer, Integer> map;

	static class Problem implements Comparable<Problem> {
		int number, level;

		public Problem(int number, int level) {
			this.number = number;
			this.level = level;
		}

		@Override
		public int compareTo(Problem o) {
			if (this.level != o.level) {
				return o.level - this.level;
			}
			return o.number - this.number;
		}
	}

	public static void main(String[] args) throws IOException {
		problems = new TreeSet<>();
		map = new HashMap<>();

		problemCnt = Integer.parseInt(br.readLine());
		for (int idx = 0; idx < problemCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			problems.add(new Problem(number, level));
			map.put(number, level);
		}

		cmdCnt = Integer.parseInt(br.readLine());
		while (cmdCnt-- > 0) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();

			if (cmd.equals("recommend")) {
				if (st.nextToken().equals("1")) {
					sb.append(problems.first().number + "\n");
				} else {
					sb.append(problems.last().number + "\n");
				}
			} else if (cmd.equals("add")) {
				int number = Integer.parseInt(st.nextToken());
				int level = Integer.parseInt(st.nextToken());
				if (map.containsKey(number)) {
					problems.remove(new Problem(number, map.get(number)));
				}
				problems.add(new Problem(number, level));
				map.put(number, level);
			} else if (cmd.equals("solved")) {
				int number = Integer.parseInt(st.nextToken());
				problems.remove(new Problem(number, map.get(number)));
				map.remove(number);
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
