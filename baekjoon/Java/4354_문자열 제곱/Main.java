import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static char[] text;
	static int[] lps; // 실패 함수 (lps: longest prefix suffix)
	static int ans;

	public static void main(String[] args) throws IOException {
		text = br.readLine().toCharArray(); // 원본 문자열

		while (text[0] != '.') {
			// 실패 함수 구하기
			// lps[i] = text[0..i]에서 접두사와 접미사가 같은 최대 길이
			lps = new int[text.length];

			int prefixLen = 0; // 현재까지 일치한 접두사 길이
			for (int idx = 1; idx < text.length; idx++) {
				// 문자 불일치 -> 이전 접두사 길이 사용
				while (prefixLen > 0 && text[idx] != text[prefixLen]) {
					prefixLen = lps[prefixLen - 1];
				}
				// 문자 일치 -> 접두사 길이 증가
				if (text[idx] == text[prefixLen])
					lps[idx] = ++prefixLen;
			}

			ans = 1;
			// 최대로 반복되는 패턴 찾기
			// 즉 text[0..pLen]이 pattern이 되고, pattern이 (text.length/pLen)번 반복되어야 한다
			for (int pLen = lps[text.length - 1]; pLen >= 1; pLen = lps[pLen - 1]) {
				if (pLen == 0) break;
				if (text.length % pLen != 0) continue;

				// text[0..pLen]이 text에서 (text.length/pLen)번 반복되는지 확인
				int pIdx = 0, cnt = 0;
				for (int tIdx = 0; tIdx < text.length; tIdx++) {
					// 문자 불일치
					if (text[tIdx] != text[pIdx]) {
						cnt = 0;
						break;
					}

					// 문자 일치
					pIdx++;

					// 패턴 일치 -> 횟수 증가
					if (pIdx == pLen) {
						cnt++;
						pIdx = 0;
					}
				}
				
				ans = Math.max(ans, cnt);
			}
			
			sb.append(ans + "\n");
			
			text = br.readLine().toCharArray();
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
