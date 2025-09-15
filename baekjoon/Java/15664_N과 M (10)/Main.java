import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static int[] element, selected;
	static boolean[] isUsed;

	// N개의 자연수 중에서 M개를 고른 수열
	// 고른 수열은 비내림차순
	// 중복되는 수열은 한 번만 출력
	static void bt(int k, int selectCnt) {
		if (selectCnt == M) {
			for (int idx = 0; idx < M; idx++) {
				sb.append(selected[idx] + " ");
			}
			sb.append("\n");
			return;
		}

		int tmp = 0;
		for (int idx = k; idx < N; idx++) {
			if (!isUsed[idx] && element[idx] != tmp) {
				selected[selectCnt] = element[idx];
				isUsed[idx] = true;
				tmp = element[idx];
				bt(idx + 1, selectCnt + 1);
				isUsed[idx] = false;
			}

		}

	}

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		element = new int[N];
		selected = new int[M];
		isUsed = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			element[idx] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(element);

		bt(0, 0);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
