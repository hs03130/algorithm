import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int plugSize, useCnt;
	static HashSet<Integer> powerStrip; // ��Ƽ�ǿ� �����ִ� ��ġ ��ȣ
	static int[] schedule; // ��ü ����
	static boolean[] isUsed; // ��Ⱑ �����ִ��� ����
	static ArrayList<ArrayList<Integer>> nextUseIdxs; // ��⺰ ���� ��� ����
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		plugSize = Integer.parseInt(st.nextToken());
		useCnt = Integer.parseInt(st.nextToken());

		schedule = new int[useCnt];
		nextUseIdxs = new ArrayList<>(); // 1-based
		for (int idx = 0; idx < 101; idx++) {
			nextUseIdxs.add(new ArrayList<>());
		}

		// ��� ����
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < useCnt; idx++) {
			int num = Integer.parseInt(st.nextToken());
			schedule[idx] = num;
			nextUseIdxs.get(num).add(idx);
		}

		// ���� Ƚ��
		powerStrip = new HashSet<>();
		isUsed = new boolean[101]; // 1-based
		for (int idx = 0; idx < useCnt; idx++) {
			int curDevice = schedule[idx];
			nextUseIdxs.get(curDevice).remove(0);

			// �Ⱦƾ� �ϴµ� ��Ƽ�ǿ� �ڸ� ����
			if (!isUsed[curDevice] && powerStrip.size() >= plugSize) {
				// ���� ���߿� ���Ǵ� ��� ����
				int maxIdx = -1, removeDevice = 0;
				for (int num : powerStrip) {
					int nextIdx = nextUseIdxs.get(num).isEmpty() ? Integer.MAX_VALUE : nextUseIdxs.get(num).get(0);
					if (nextIdx > maxIdx) {
						removeDevice = num;
						maxIdx = nextIdx;
					}
				}
				powerStrip.remove(removeDevice);
				isUsed[removeDevice] = false;
				ans++;
			}

			// ���ο� ��� �ȱ�
			powerStrip.add(curDevice);
			isUsed[curDevice] = true;
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
