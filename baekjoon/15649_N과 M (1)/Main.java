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

	static int N, M;
	static int[] arr;
	static boolean[] isUsed;

	// 1부터 N까지의 자연수 중에서 중복 없이 M개 고르기
	static void bt(int selectCnt) {
		if (selectCnt == M) {
			for (int idx = 0; idx < M; idx++) {
				sb.append(arr[idx] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int idx = 0; idx < N; idx++) {
			if (!isUsed[idx]) {
				arr[selectCnt] = idx + 1;
				isUsed[idx] = true;
				bt(selectCnt + 1);
				isUsed[idx] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];
		isUsed = new boolean[N];

		bt(0);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
