import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static char[] text, pattern;
	static int[] lps; // 실패 함수 (lps: longest prefix suffix)
	static int ansCnt = 0;

	public static void main(String[] args) throws IOException {
		text = br.readLine().toCharArray(); // 원본 문자열
		pattern = br.readLine().toCharArray(); // 찾을 문자열

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
		for (int tIdx = 0; tIdx < text.length; tIdx++) {
			// 문자 불일치 -> 실패 함수를 사용해서 pattern 인덱스 이동
			while (pIdx > 0 && text[tIdx] != pattern[pIdx]) {
				pIdx = lps[pIdx - 1];
			}
			// 문자 일치 -> pattern 인덱스 증가
			if (text[tIdx] == pattern[pIdx])
				pIdx++;

			// pattern 전체 일치
			if (pIdx == pattern.length) {
				ansCnt++; // 일치 횟수 증가
				sb.append((tIdx - pattern.length + 2) + " "); // 패턴이 일치한 위치
				pIdx = lps[pIdx - 1];
			}
		}

		bw.write(String.valueOf(ansCnt) + "\n" + sb.toString());
		bw.flush();
		bw.close();
	}

}
