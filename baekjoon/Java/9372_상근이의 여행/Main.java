/*
 * 비행기를 타는 횟수가 아니고 비행기 종류의 최소 개수이므로 최소 스패닝 트리 문제이다.
 * 최소 스패닝 트리에서 필요한 간선은 (정점 수 - 1)개이다.
 */
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

	static int testCase, nodeCnt, edgeCnt;

	public static void main(String[] args) throws IOException {
		testCase = Integer.parseInt(br.readLine());

		while (testCase-- > 0) {
			st = new StringTokenizer(br.readLine());
			nodeCnt = Integer.parseInt(st.nextToken());
			edgeCnt = Integer.parseInt(st.nextToken());

			for (int idx = 0; idx < edgeCnt; idx++) {
				br.readLine();
			}

			sb.append(String.valueOf(nodeCnt - 1) + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
