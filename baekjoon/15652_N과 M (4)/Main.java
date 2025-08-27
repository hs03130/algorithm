import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static int[] arr;

	// 1���� N������ �ڿ��� �߿��� M�� ����
	// ���� ���� ������ ��� �ȴ�
	// �� ������ �񳻸�����
	static void bt(int selectCnt) {
		if (selectCnt == M) {
			for (int idx = 0; idx < M; idx++) {
				sb.append(arr[idx] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int idx = selectCnt == 0 ? 1 : arr[selectCnt - 1]; idx <= N; idx++) {
			arr[selectCnt] = idx;
			bt(selectCnt + 1);
		}
	}

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];
		bt(0);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
