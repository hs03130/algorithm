import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int size;
	static char[] pattern;
	static int[] lps; // 실패 함수 (lps: longest prefix suffix)

	public static void main(String[] args) throws IOException {
		size = Integer.parseInt(br.readLine());
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
			if (pattern[idx] == pattern[prefixLen]) lps[idx] = ++prefixLen;
		}

		// 접두사와 접미사가 일치하는 길이를 빼면 최소 광고
		bw.write(String.valueOf(size - lps[size - 1]));
		bw.flush();
		bw.close();
	}

}
