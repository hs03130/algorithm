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

	static int classCnt, studentCnt;
	static int[][] classes; // 학급별 학생 능력치
	static int[] selected; // 학급별 선택된 학생 인덱스
	static int minClass = 0, maxClass = 0;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		classCnt = Integer.parseInt(st.nextToken());
		studentCnt = Integer.parseInt(st.nextToken());

		classes = new int[classCnt][studentCnt];
		selected = new int[classCnt];
		for (int c = 0; c < classCnt; c++) {
			st = new StringTokenizer(br.readLine());
			for (int student = 0; student < studentCnt; student++) {
				classes[c][student] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(classes[c]);
			if (classes[c][selected[c]] < classes[minClass][selected[minClass]]) {
				minClass = c;
			}
			if (classes[c][selected[c]] >= classes[maxClass][selected[maxClass]]) {
				maxClass = c;
			}
		}
		ans = classes[maxClass][selected[minClass]] - classes[minClass][selected[minClass]];

		while (selected[minClass] < studentCnt - 1) {
			// 능력치가 제일 작은 반은 다음 사람 선택
			++selected[minClass];

			// 최소 능력치, 최대 능력치 갱신
			for (int c = 0; c < classCnt; c++) {
				if (classes[c][selected[c]] < classes[minClass][selected[minClass]]) {
					minClass = c;
				}
				if (classes[c][selected[c]] >= classes[maxClass][selected[maxClass]]) {
					maxClass = c;
				}
			}

			// 최대, 최소값 비교
			if (classes[maxClass][selected[maxClass]] - classes[minClass][selected[minClass]] < ans) {
				ans = classes[maxClass][selected[maxClass]] - classes[minClass][selected[minClass]];
			}
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
