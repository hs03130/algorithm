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

	static int nodeCnt, praiseCnt;
	static int[] parents, scores, praises;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		praiseCnt = Integer.parseInt(st.nextToken());

		parents = new int[nodeCnt + 1];
		scores = new int[nodeCnt + 1];
		praises = new int[nodeCnt + 1];

		st = new StringTokenizer(br.readLine());
		for (int idx = 1; idx <= nodeCnt; idx++) {
			parents[idx] = Integer.parseInt(st.nextToken());
		}
		parents[1] = 0;

		for (int idx = 0; idx < praiseCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int person = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			praises[person] += score;
		}

		for (int idx = 1; idx <= nodeCnt; idx++) {
			scores[idx] = scores[parents[idx]] + praises[idx];
			sb.append(scores[idx] + " ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
