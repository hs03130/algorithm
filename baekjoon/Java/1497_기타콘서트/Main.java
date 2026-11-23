import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int guitarCnt, songCnt;
	static long[] playList;
	static int minGuitarCnt = Integer.MAX_VALUE;
	static long maxSongCnt = 0L;

	static void func(int cur, int gCnt, long play) {
		int sCnt = Long.bitCount(play);
		if (sCnt > maxSongCnt || (sCnt == maxSongCnt && gCnt < minGuitarCnt)) {
			maxSongCnt = sCnt;
			minGuitarCnt = gCnt;
		}

		if (cur == guitarCnt) return;

		// 기타 구매하지 않음
		func(cur + 1, gCnt, play);

		// 기타 구매
		func(cur + 1, gCnt + 1, play | playList[cur]);
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		guitarCnt = Integer.parseInt(st.nextToken());
		songCnt = Integer.parseInt(st.nextToken());

		playList = new long[guitarCnt];
		for (int idx = 0; idx < guitarCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken(); // 기타 이름은 사용 안함
			String song = st.nextToken();

			// 연주할 수 있는 곡 정보를 이진수로 표현
			String binary = song.replace('Y', '1').replace('N', '0');
			playList[idx] = Long.parseLong(binary, 2);
		}

		// 가장 많은 곡을 연주할 수 있는 기타 조합 찾기
		func(0, 0, 0); // 인덱스, 구매한 기타 수, 연주 정보

		if (maxSongCnt == 0) sb.append("-1");
		else sb.append(minGuitarCnt);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}