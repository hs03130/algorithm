import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int studentCnt;
	static int[][] students;
	static TreeSet<Team> teams; // 팀 별 인원
	static int ans = 0;

	static final int HEIGHT = 0, SEQUENCE = 1;

	static class Team implements Comparable<Team> {
		int number, count;

		public Team(int number, int count) {
			this.number = number;
			this.count = count;
		}

		@Override
		public int compareTo(Team o) {
			if (this.count != o.count) {
				return this.count - o.count;
			}
			return this.number - o.number;
		}
	}

	public static void main(String[] args) throws IOException {
		studentCnt = Integer.parseInt(br.readLine());
		students = new int[studentCnt][2];
		for (int idx = 0; idx < studentCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			students[idx][HEIGHT] = Integer.parseInt(st.nextToken());
			students[idx][SEQUENCE] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(students, (o1, o2) -> {
			return o2[HEIGHT] - o1[HEIGHT];
		});

		teams = new TreeSet<>();
		// 키가 큰 학생부터 팀 구성
		for (int idx = 0; idx < studentCnt; idx++) {
			// 학생의 최소 등수보다 적은 팀을 찾아 배정
			Team team = teams.lower(new Team(-1, students[idx][SEQUENCE]));
			if (team != null) {
				teams.remove(team);
				team.count++;
				teams.add(team);
			} else {
				teams.add(new Team(ans++, 1));
			}
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
