import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int len, cnt = 0;
	static char[] text, pattern;
	static int[] lps; // 실패 함수 (lps: longest prefix suffix)

	// 최대 공약수
	static int gcd(int a, int b) {
		while (b != 0) {
			int tmp = a % b;
			a = b;
			b = tmp;
		}
		return a;
	}

	public static void main(String[] args) throws IOException {
		len = Integer.parseInt(br.readLine());

		pattern = new char[len]; // 찾을 문자열
		text = new char[len]; // 원본 문자열

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < len; idx++) {
			pattern[idx] = st.nextToken().charAt(0);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < len; idx++) {
			text[idx] = st.nextToken().charAt(0);
		}

		// 실패 함수 구하기
		// lps[i] = pattern[0..i]에서 접두사와 접미사가 같은 최대 길이
		lps = new int[pattern.length];

		int prefixLen = 0; // 현재까지 일치한 접두사 길이
		for (int idx = 1; idx < pattern.length; idx++) {
			// 문자 불일치 -> 이전 접두사 길이 사용
			while (prefixLen > 0 && pattern[idx] != pattern[prefixLen]) {
				prefixLen = lps[prefixLen - 1];
			}
			// 문자 일치 -> 접두사 길이 증가
			if (pattern[idx] == pattern[prefixLen])
				lps[idx] = ++prefixLen;
		}

		// KMP 문자열 탐색
		int pIdx = 0;
		for (int tIdx = 0; tIdx < (len * 2) - 1; tIdx++) {
			// 문자 불일치 -> 실패 함수를 사용해서 pattern 인덱스 이동
			while (pIdx > 0 && text[tIdx % len] != pattern[pIdx]) {
				pIdx = lps[pIdx - 1];
			}
			// 문자 일치 -> pattern 인덱스 증가
			if (text[tIdx % len] == pattern[pIdx]) pIdx++;

			// pattern 전체 일치
			if (pIdx == pattern.length) {
				cnt++; // 부분 문자열 존재
				pIdx = lps[pIdx - 1];
			}
		}
		
		// 기약분수로 나타내기
		int g = gcd(cnt, len);

		bw.write((int) cnt / g + "/" + (int) len / g);
		bw.flush();
		bw.close();
	}

}
