import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static int testCase, orderLen;
	static char[] order, text, pattern;
	static int[] origin, modified, cnt;
	static int[] lps; // 실패 함수 (lps: longest prefix suffix)
	static ArrayList<Integer> ans;

	static final int SIZE = 127;

	public static void main(String[] args) throws IOException {
		testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			order = br.readLine().toCharArray(); // 문자 순서
			pattern = br.readLine().toCharArray(); // 찾을 문자열
			text = br.readLine().toCharArray(); // 암호화된 문자열

			orderLen = order.length;

			origin = new int[SIZE]; // 원본 문자 순서
			modified = new int[SIZE]; // 옮긴 문자 순서
			cnt = new int[orderLen]; // 쉬프트값 별로 패턴이 일치한 회수

			for (int idx = 0; idx < orderLen; idx++) {
				origin[order[idx]] = idx;
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
				if (pattern[idx] == pattern[prefixLen]) lps[idx] = ++prefixLen;
			}

			// 시프트 값을 변경하면서 패턴이 일치하는 횟수 세기
			for (int diff = 0; diff < orderLen; diff++) {
				// 시프트 값대로 문자 순서를 변경
				for (int idx = 0; idx < orderLen; idx++) {
					modified[order[idx]] = (origin[order[idx]] + diff) % orderLen;
				}

				// 바꾼 문자 순서를 참고하여 KMP 문자열 탐색
				int pIdx = 0;
				for (int tIdx = 0; tIdx < text.length; tIdx++) {
					// 문자 불일치 -> 실패 함수를 사용해서 pattern 인덱스 이동
					while (pIdx > 0 && origin[text[tIdx]] != modified[pattern[pIdx]]) {
						pIdx = lps[pIdx - 1];
					}
					// 문자 일치 -> pattern 인덱스 증가
					if (origin[text[tIdx]] == modified[pattern[pIdx]]) pIdx++;

					// pattern 전체 일치
					if (pIdx == pattern.length) {
						cnt[diff]++;
						pIdx = lps[pIdx - 1];
					}
				}

			}

			// 출력
			ans = new ArrayList<>();
			for (int shift = 0; shift < orderLen; shift++) {
				// 딱 한번만 나타나야함
				if (cnt[shift] == 1) ans.add(shift);
			}

			if (ans.size() == 0) {
				sb.append("no solution");
			} else {
				if (ans.size() == 1) sb.append("unique:");
				else sb.append("ambiguous:");

				for (int shift : ans) {
					sb.append(" " + shift);
				}
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
