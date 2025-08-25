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

	// 1부터 N까지의 자연수 중에서 중복 없이 M개 고르기
	// 고른 수열은 오름차순
	static void bt(int k, int selectCnt) {
		if (selectCnt == M) {
			for (int idx = 0; idx < M; idx++) {
				sb.append(arr[idx] + " ");
			}
			sb.append("\n");
			return;
		}

		if (k == N + 1) {
			return;
		}

		// 포함
		arr[selectCnt] = k;
		bt(k + 1, selectCnt + 1);
		
		// 미포함
		bt(k + 1, selectCnt);

	}

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];

		bt(1, 0);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
