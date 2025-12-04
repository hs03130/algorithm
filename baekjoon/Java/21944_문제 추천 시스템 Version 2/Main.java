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

	static int problemCnt, queryCnt;
	static int[][] problems;
	static TreeSet<Problem> problemByLevel;
	static TreeSet<Problem>[] problemByGroup;

	static final int LEVEL = 0, GROUP = 1;

	static class Problem implements Comparable<Problem> {
		int number, level;

		public Problem(int number, int level) {
			this.number = number;
			this.level = level;
		}

		@Override
		public int compareTo(Problem o) {
			if (this.level != o.level) {
				return this.level - o.level;
			}
			return this.number - o.number;
		}
	}

	public static void main(String[] args) throws IOException {
		problems = new int[100001][2]; // 1-based
		problemByLevel = new TreeSet<>();
		problemByGroup = new TreeSet[101]; // 1-based
		for (int group = 1; group <= 100; group++) {
			problemByGroup[group] = new TreeSet<>();
		}

		problemCnt = Integer.parseInt(br.readLine());
		for (int idx = 0; idx < problemCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			int group = Integer.parseInt(st.nextToken());

			problems[number][LEVEL] = level;
			problems[number][GROUP] = group;
			Problem problem = new Problem(number, level);
			problemByLevel.add(problem);
			problemByGroup[group].add(problem);
		}

		queryCnt = Integer.parseInt(br.readLine());
		while (queryCnt-- > 0) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			if (cmd.equals("recommend")) {
				int group = Integer.parseInt(st.nextToken());
				int type = Integer.parseInt(st.nextToken());
				if (type == 1) {
					sb.append(problemByGroup[group].last().number + "\n");
				} else {
					sb.append(problemByGroup[group].first().number + "\n");
				}
			} else if (cmd.equals("recommend2")) {
				int type = Integer.parseInt(st.nextToken());
				if (type == 1) {
					sb.append(problemByLevel.last().number + "\n");
				} else {
					sb.append(problemByLevel.first().number + "\n");
				}
			} else if (cmd.equals("recommend3")) {
				int type = Integer.parseInt(st.nextToken());
				Problem problem = new Problem(0, Integer.parseInt(st.nextToken()));
				Problem find;
				if (type == 1) {
					find = problemByLevel.ceiling(problem);
				} else {
					find = problemByLevel.floor(problem);
				}
				if (find != null) {
					sb.append(find.number + "\n");
				} else {
					sb.append("-1 \n");
				}
			} else if (cmd.equals("add")) {	
				int number = Integer.parseInt(st.nextToken());
				int level = Integer.parseInt(st.nextToken());
				int group = Integer.parseInt(st.nextToken());
				problems[number][LEVEL] = level;
				problems[number][GROUP] = group;
				Problem problem = new Problem(number, level);
				problemByLevel.add(problem);
				problemByGroup[group].add(problem);
			} else if (cmd.equals("solved")) {
				int number = Integer.parseInt(st.nextToken());
				Problem problem = new Problem(number, problems[number][LEVEL]);
				problemByLevel.remove(problem);
				problemByGroup[problems[number][GROUP]].remove(problem);
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
