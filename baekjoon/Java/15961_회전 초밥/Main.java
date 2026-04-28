/*
 * 연속으로 먹을수있는 접시 개수를 범위로 잡고, 초밥 종류를 센다.
 * 접시 개수가 넘는다면 큐에 가장 먼저 들어온 초밥을 뺀다.
 * 빼고 나서 큐에 똑같은 초밥이 남아있지 않는다면 종류 감소
 * 순환하므로 마지막입력된 초밥부터 연속으로 먹을 수 있는 접시 개수만큼은 더 확인해야한다.
 * 종류에 서비스 초밥이 포함되어있지 않다면 +1 해준다.
 * 
 * 1. 입력
 *  1-1 총 접시 수 | 초밥 종류 | 연속으로 먹을 수 있는 접시 개수 | 서비스 초밥
 *  1-2 초밥 순서대로
 * 
 * 2. 연속된 N개의 접시에서 최대 초밥 종류 세기 (접시는 순환)
 *  2-1 범위 N을 유지하면서 초밥 종류를 센다.
 *      이때 1부터 N개 만큼은 배열로 따로 저장해둔다
 *  2-2 끝 초밥을 마지막으로 확인했다면 배열에 저장해둔 초밥을 이어서 확인한다.
 *  2-3 초밥 종류에 서비스 초밥이 포함되어 있지 않다면 +1 해준다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int dishCnt; // 총 접시 개수
	static int sushiCnt; // 초밥 종류
	static int eatCnt; // 연속으로 확인할 접시
	static int coupon; // 서비스 초밥
	static int[] elementList;
	static int[] selected;
	static Deque<Integer> Q;
	static int cnt;
	static int ans;

	// 입력
	static void input() throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		dishCnt = Integer.parseInt(st.nextToken());
		sushiCnt = Integer.parseInt(st.nextToken());
		eatCnt = Integer.parseInt(st.nextToken());
		coupon = Integer.parseInt(st.nextToken());
	}

	static void solve() throws Exception {
		ans = 0;
		elementList = new int[eatCnt];
		selected = new int[sushiCnt + 1];
		// 큐로 범위 eatCnt개를 유지하면서 큐에 포함된 초밥 종류 세기
		Q = new ArrayDeque<>();
		cnt = 0;
		// 1부터 eatCnt개까지의 초밥은 저장해둔다 <- 초밥은 순환
		for (int idx = 0; idx < eatCnt; idx++) {
			elementList[idx] = Integer.parseInt(br.readLine().trim());
			check(elementList[idx]);
			// 연속으로 확인할 수 있는 접시 개수+1만큼 찾았다면 바로 종료
			if (ans == eatCnt + 1) {
				return;
			}
		}
		for (int idx = eatCnt; idx < dishCnt; idx++) {
			check(Integer.parseInt(br.readLine().trim()));
			if (ans == eatCnt + 1) {
				return;
			}
		}
		// 마지막 초밥까지 확인하고 나면 다시 처음 초밥으로 이어서 확인
		for (int idx = 0; idx < eatCnt; idx++) {
			check(elementList[idx]);
			if (ans == eatCnt + 1) {
				return;
			}
		}
	}

	static void check(int sushi) {
		if (selected[sushi] == 0) {
			cnt++;
		}
		selected[sushi]++;
		Q.offer(sushi);

		if (Q.size() > eatCnt) {
			selected[Q.peek()]--;
			if (selected[Q.peek()] == 0) {
				cnt--;
			}
			Q.poll();
		}

		if (selected[coupon] > 0) {
			ans = Math.max(ans, cnt);
		} else {
			ans = Math.max(ans, cnt + 1);
		}
		if (ans == eatCnt + 1) {
			return;
		}

	}

	public static void main(String[] args) throws Exception {
		input();
		solve();
		System.out.println(ans);
	}

}