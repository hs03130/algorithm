import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int problemCnt;
	static Problem[] problems;
	static PriorityQueue<Problem> pq;
	static int ans = 0;

	static class Problem {
		int deadline, reward;

		public Problem(int deadline, int reward) {
			this.deadline = deadline;
			this.reward = reward;
		}

	}

	public static void main(String[] args) throws IOException {
		problemCnt = Integer.parseInt(br.readLine());
		problems = new Problem[problemCnt];
		pq = new PriorityQueue<>((o1, o2) -> { // 보상 내림차순으로 정렬
			return o2.reward - o1.reward;
		});

		for (int idx = 0; idx < problemCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			problems[idx] = new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(problems, (o1, o2) -> { // 데드라인 오름차순으로 정렬
			return o1.deadline - o2.deadline;
		});

		for (int t = 200000, idx = problemCnt - 1; t >= 0; t--) {
			// t시간에 데드라인을 넘지 않는 문제들을 pq에 추가
			while (idx >= 0 && problems[idx].deadline > t) {
				pq.add(problems[idx--]);
			}

			// 보상이 가장 큰 문제 풀기
			if (!pq.isEmpty()) {
				ans += pq.poll().reward;
			}
		}
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
