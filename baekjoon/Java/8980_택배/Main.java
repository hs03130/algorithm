/*
 * ȸ�ǽ� ������ ����� ������
 * ���� ���� �� �ִ� ���ڸ� �켱������ Ʈ���� �ƴ´�.
 * Ʈ�� �뷮�� �Ѱ谡 �����Ƿ� ���ڸ� �ư� ���������� ��ġ�� �������� ���Ը� ���ϰ�
 * ���� �ʴ� ������ ���߿� ���� ���ڸ� �ƴ´�.
 * 
 * �Ʒ� �������� 1>2, 3>4�� ���� ������
 * 2>5�� ��� Ʈ�� ũ�� 60���� 3>4�� 40�� �� 20���� ���� �� �ִ�.
 * 1>6�� ��� 2~5���� 60�� ��� ä���� ���� �� ����
 * 5>6�� 3>4, 2>5�� �������Ƿ� �ٽ� ä�� �� �ִ�.
 * 6 60
 * 5
 * 1 2 30
 * 3 4 40
 * 2 5 70
 * 1 6 40
 * 5 6 60
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int townCnt, truckSize, boxCnt;
	static ArrayList<Box> boxes;
	static int[] truck; // idx �������� Ʈ���� ���� �ڽ� ����
	static int ans = 0;

	static final int MAX_TOWN_CNT = 2000;

	static class Box {
		int from, to, cnt;

		public Box(int from, int to, int cnt) {
			this.from = from;
			this.to = to;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		townCnt = Integer.parseInt(st.nextToken());
		truckSize = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		boxCnt = Integer.parseInt(st.nextToken());

		boxes = new ArrayList<>();
		for (int idx = 0; idx < boxCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			boxes.add(new Box(from, to, cnt));
		}

		boxes.sort((b1, b2) -> {
			// ���� �����ϴ� �ͺ��� ���� �ƴ´�
			if (b1.to != b2.to) return b1.to - b2.to;
			return b2.from - b1.from;
		});

		truck = new int[MAX_TOWN_CNT + 1];
		for (Box box : boxes) {
			for (int town = box.from; town < box.to; town++) {
				// cnt�� Ȥ�� Ʈ�� �ִ� �뷮���� �ƴ´�
				box.cnt = Math.min(box.cnt, truckSize - truck[town]);
			}
			ans += box.cnt;
			for (int town = box.from; town < box.to; town++) {
				truck[town] += box.cnt;
			}
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
