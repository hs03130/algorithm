import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int inningCnt; // 이닝 수
	static int[][] inningResult; // 이닝별 타자 기록
	static int[] selectList; // 타순
	static int base;
	static int ans; // 최고 점수

	static final int OUT = 0;
	static final int SINGLE = 1;
	static final int DOUBLE = 2;
	static final int TRIPLE = 3;
	static final int HOMERUN = 4;
	static final int HITTER_CNT = 9;
	static final int EMPTY = 0;
	static final int PLAYER = 1;

	// 입력
	static void input() throws IOException {
		// 이닝수 입력
		inningCnt = Integer.parseInt(br.readLine().trim());
		// 이닝별 타자 기록
		inningResult = new int[inningCnt][HITTER_CNT];
		for (int inningIdx = 0; inningIdx < inningCnt; inningIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int hitterIdx = 0; hitterIdx < HITTER_CNT; hitterIdx++) {
				inningResult[inningIdx][hitterIdx] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void solve() {
		ans = 0;

		selectList = new int[HITTER_CNT];
		// 타순 정하기
		permutation(0, 1);

	}

	// 타순 정하기
	static void permutation(int selectIdx, int visited) {
		// 타순 결정 완료
		if (selectIdx == HITTER_CNT) {
			// 경기 시뮬레이션
			play();
			return;
		}

		// 4번타자는 항상 1번 선수
		if (selectIdx == 3) {
			selectList[selectIdx] = 0;
			permutation(selectIdx + 1, visited);
		}
		// 나머지 타순 정하기
		else {
			for (int hitterIdx = 1; hitterIdx < HITTER_CNT; hitterIdx++) {
				// 아직 정하지 않은 선수만 선택
				if ((visited & 1 << hitterIdx) == 0) {
					selectList[selectIdx] = hitterIdx;
					// 계속 진행
					permutation(selectIdx + 1, visited | (1 << hitterIdx));
				}
			}
		}
	}

	// 경기 시뮬레이션
	static void play() {
		// 경기 시작
		int hitter = 0; // 1번 타자부터 시작
		int score = 0; // 경기 결과

		// 이닝 수 만큼 반복
		for (int inning = 0; inning < inningCnt; inning++) {
			// 이닝 시작
			base = 0;
			int outCnt = 0;
			// 3 아웃될 때까지 공격
			while (outCnt < 3) {
				int result = inningResult[inning][selectList[hitter % 9]];
				// 안타
				switch (result) {
					case OUT:
						outCnt++;
						break;
					case HOMERUN:
						score++;
					case TRIPLE:
						if (((base <<= 1) & (1 << 3)) != 0) { score++; }
					case DOUBLE:
						if (((base <<= 1) & (1 << 3)) != 0) { score++; }
					case SINGLE:
						if (((base <<= 1) & (1 << 3)) != 0) { score++; }
						// 진루
						base |= 1 << (result - 1);
						base &= 7;
				}
				if (result == HOMERUN) base = 0;
					
				// 다음 타순
				hitter++;
			}
		}

		// 경기 결과 최고점과 비교,갱신
		ans = Math.max(ans, score);
	}

	public static void main(String args[]) throws IOException {
		input();
		solve();
		System.out.println(ans);
	}

}