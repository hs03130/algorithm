/*
 * 회의실 배정과 비슷한 문제로
 * 빨리 내릴 수 있는 상자를 우선적으로 트럭에 싣는다.
 * 트럭 용량에 한계가 있으므로 상자를 싣고 내릴때까지 거치는 마을마다 무게를 더하고
 * 넘지 않는 선에서 나중에 내릴 상자를 싣는다.
 * 
 * 아래 예제에서 1>2, 3>4를 먼저 실으면
 * 2>5의 경우 트럭 크기 60에서 3>4의 40을 뺀 20개만 실을 수 있다.
 * 1>6의 경우 2~5까지 60을 모두 채워서 실을 수 없고
 * 5>6은 3>4, 2>5를 내렸으므로 다시 채울 수 있다.
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
	static int[] truck; // idx 마을에서 트럭에 실은 박스 개수
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
			// 빨리 하차하는 것부터 먼저 싣는다
			if (b1.to != b2.to) return b1.to - b2.to;
			return b2.from - b1.from;
		});

		truck = new int[MAX_TOWN_CNT + 1];
		for (Box box : boxes) {
			for (int town = box.from; town < box.to; town++) {
				// cnt개 혹은 트럭 최대 용량까지 싣는다
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
