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

	static int spaceCnt, planetCnt;
	static int[][] spaces;
	static int[] sortedSpace;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		spaceCnt = Integer.parseInt(st.nextToken());
		planetCnt = Integer.parseInt(st.nextToken());

		spaces = new int[spaceCnt][planetCnt];
		sortedSpace = new int[planetCnt];
		for (int spaceIdx = 0; spaceIdx < spaceCnt; spaceIdx++) {
			st = new StringTokenizer(br.readLine());
			for (int planetIdx = 0; planetIdx < planetCnt; planetIdx++) {
				spaces[spaceIdx][planetIdx] = Integer.parseInt(st.nextToken());
				sortedSpace[planetIdx] = spaces[spaceIdx][planetIdx];
			}

			// ÁÂÇ¥ ¾ÐÃà
			Arrays.sort(sortedSpace);
			for (int idx = 0; idx < planetCnt; idx++) {
				int left = -1, right = planetCnt - 1;
				while (left + 1 < right) {
					int mid = (left + right) / 2;
					if (sortedSpace[mid] < spaces[spaceIdx][idx])
						left = mid;
					else
						right = mid;
				}
				spaces[spaceIdx][idx] = right;
			}
		}

		// ±ÕµîÇÑ ½Ö ¼¼±â
		for (int row = 0; row < spaceCnt - 1; row++) {
			for (int col = row + 1; col < spaceCnt; col++) {
				if (Arrays.equals(spaces[row], spaces[col]))
					ans++;
			}
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}