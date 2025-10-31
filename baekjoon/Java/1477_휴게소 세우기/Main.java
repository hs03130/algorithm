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

	static int currentCnt, remainCnt, roadLen;
	static int[] services;
	static int ans = 0;

	// len 길이로 휴게소를 배치했을 때 remainCnt개 초과 배치 가능한지 => remainCnt개 배치했을 때 최대 길이가 len이 아님
	static boolean check(int len) {
		int cnt = 0;
		for (int idx = 1; idx < currentCnt + 2; idx++) {
			int tmpLen = services[idx] - services[idx - 1] - 1;
			cnt += tmpLen / len;
		}
		return cnt > remainCnt;
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		currentCnt = Integer.parseInt(st.nextToken());
		remainCnt = Integer.parseInt(st.nextToken());
		roadLen = Integer.parseInt(st.nextToken());
		services = new int[currentCnt + 2];

		if (currentCnt > 0) {
			st = new StringTokenizer(br.readLine());
		}
		for (int idx = 1; idx <= currentCnt; idx++) {
			services[idx] = Integer.parseInt(st.nextToken());
		}
		// 0부터 첫번째 휴게소 사이, 마지막 휴게소부터 도로끝 사이에도 휴게소를 세울 수 있음
		services[0] = 0;
		services[currentCnt + 1] = roadLen;
		Arrays.sort(services);

		int left = 0, right = roadLen - 1;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (check(mid)) {
				left = mid;
			} else {
				right = mid;
			}
		}

		sb.append(right);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
