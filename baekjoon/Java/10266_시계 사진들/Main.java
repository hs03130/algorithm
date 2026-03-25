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
	static StringTokenizer st;

	static int num;
	static int[] pattern, text;
	static int[] lps; // 실패 함수 (lps: longest prefix suffix)
	static String ans = "impossible";

	static final int MAX = 360_000;

	public static void main(String[] args) throws IOException {
		num = Integer.parseInt(br.readLine());

		pattern = new int[num + 1];
		text = new int[num + 1];

		// 각도 입력
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < num; idx++) {
			pattern[idx] = Integer.parseInt(st.nextToken());
		}
		pattern[num] = MAX; // num-1번째 각도와 0번째 각도의 차이값을 구하기 위한 용도
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < num; idx++) {
			text[idx] = Integer.parseInt(st.nextToken());
		}
		text[num] = MAX; // num-1번째 각도와 0번째 각도의 차이값을 구하기 위한 용도

		// 각도 정렬
		Arrays.sort(pattern);
		Arrays.sort(text);
		pattern[num] += pattern[0];
		text[num] += text[0];

		// 앞뒤 각도 차이 계산
		for (int idx = 0; idx < num; idx++) {
			pattern[idx] = pattern[idx + 1] - pattern[idx];
			text[idx] = text[idx + 1] - text[idx];
		}

		// 실패 함수 구하기
		// lps[i] = pattern[0..i]에서 접두사와 접미사가 같은 최대 길이
		lps = new int[num];

		int prefixLen = 0; // 현재까지 일치한 개수
		for (int idx = 1; idx < num; idx++) {
			// 불일치 -> 이전 길이 사용
			while (prefixLen > 0 && pattern[idx] != pattern[prefixLen]) {
				prefixLen = lps[prefixLen - 1];
			}
			// 일치 -> 길이 증가
			if (pattern[idx] == pattern[prefixLen]) lps[idx] = ++prefixLen;
		}

		// KMP 탐색
		int pIdx = 0;
		for (int tIdx = 0; tIdx < (num * 2) - 1; tIdx++) {
			// 불일치 -> 실패 함수를 사용해서 pattern 인덱스 이동
			while (pIdx > 0 && text[tIdx % num] != pattern[pIdx]) {
				pIdx = lps[pIdx - 1];
			}
			// 일치 -> pattern 인덱스 증가
			if (text[tIdx % num] == pattern[pIdx]) pIdx++;

			// pattern 전체 일치
			if (pIdx == num) {
				ans = "possible";
				break;
			}
		}

		bw.write(ans);
		bw.flush();
		bw.close();
	}

}

/*
 * 각 시계 바늘 사이의 각도 차이가 두 시계가 동일하면 같은 시각을 나타낼 수 있다.
 * 
 * 입력된 각도를 정렬하여 앞뒤 바늘 간의 각도 차이를 구한다. (원형 패턴임을 유의) 
 * pattern 시계에 대해 실패 함수를 구한다.
 * text와 비교하면서 각도 차이가 일치하는지 확인한다.
 */