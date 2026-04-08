import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int cnt;
	static ArrayDeque<int[]> stack;
	static long ans;

	static final int HEIGHT = 0, IDX = 1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		while ((cnt = Integer.parseInt(st.nextToken())) > 0) {
			stack = new ArrayDeque<>();
			ans = 0L;

			for (int idx = 0; idx < cnt; idx++) {
				int height = Integer.parseInt(st.nextToken());

				int curHeightLeftIdx = idx; // 현재 높이 이상의 직사각형이 가장 먼저 나온 위치
				while (!stack.isEmpty() && height <= stack.peek()[HEIGHT]) {
					ans = Math.max(ans, (long) (idx - stack.peek()[IDX]) * stack.peek()[HEIGHT]);
					curHeightLeftIdx = stack.peek()[IDX];
					stack.pop();
				}

				stack.push(new int[] { height, curHeightLeftIdx });
			}

			// 마지막 직사각형 확인
			while (!stack.isEmpty()) {
				ans = Math.max(ans, (long) (cnt - stack.peek()[IDX]) * stack.peek()[HEIGHT]);
				stack.pop();
			}

			// 출력
			sb.append(ans + "\n");

			st = new StringTokenizer(br.readLine());
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}