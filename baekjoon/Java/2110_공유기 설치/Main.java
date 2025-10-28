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

	static int homeCnt, wifiCnt;
	static int[] homes;

	static int lowerBound(int num) {
		int left = -1, right = homeCnt;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (homes[mid] < num) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return right;
	}

	// 길이 len으로 wifiCnt만큼 설치할 수 있는지
	static boolean check(int len) {
		int idx = 0, cnt = 0;
		while (idx < homeCnt) {
			idx = lowerBound(homes[idx] + len);
			cnt++;
		}
		return cnt >= wifiCnt;
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		homeCnt = Integer.parseInt(st.nextToken());
		wifiCnt = Integer.parseInt(st.nextToken());
		homes = new int[homeCnt];
		for (int idx = 0; idx < homeCnt; idx++) {
			homes[idx] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(homes);

		// 두 공유기 사이의 최대 거리 찾기
		int left = 1, right = 1000000001;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (check(mid)) {
				left = mid;
			} else {
				right = mid;
			}
		}

		sb.append(left);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
