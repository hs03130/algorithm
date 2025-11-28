import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int elementCnt;
	static HashMap<String, Integer> map;

	static int ans;

	static final int TYPE = 1;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			elementCnt = Integer.parseInt(br.readLine());

			// 각 종류별 개수 세기
			map = new HashMap<>();
			for (int elem = 0; elem < elementCnt; elem++) {
				String type = br.readLine().split(" ")[TYPE];
				if (!map.containsKey(type)) {
					map.put(type, 1);
				} else {
					map.put(type, map.get(type) + 1);
				}
			}

			// 풀이
			ans = 1;

			Collection<Integer> cntList = map.values();
			for (int cnt : cntList) {
				ans *= cnt + 1;
			}

			ans -= 1;

			// 출력
			System.out.println(ans);
		}
	}

}