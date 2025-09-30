import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int classCnt, mainCover, subCover;
	static int[] studentCnts;
	static long ans = 0;

	public static void main(String[] args) throws IOException {

		classCnt = Integer.parseInt(br.readLine());

		studentCnts = new int[classCnt];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < classCnt; idx++) {
			studentCnts[idx] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		mainCover = Integer.parseInt(st.nextToken());
		subCover = Integer.parseInt(st.nextToken());

		// ���� �������� ���ǿ� �Ѹ� ��ġ
		ans += classCnt;
		for (int idx = 0; idx < classCnt; idx++) {
			// �ΰ������� ������ �л���
			studentCnts[idx] -= mainCover;

			// �� �ݿ� �ʿ��� �ΰ����� ��
			if (studentCnts[idx] > 0) {
				ans += (int) Math.ceil((double) studentCnts[idx] / subCover);
			}
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
