import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int nodeCnt, railroTicketPrice, visitCityCnt, edgeCnt;
	static HashMap<String, Integer> city;
	static int[] route;
	static int[][] adj, railroAdj;

	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		railroTicketPrice = Integer.parseInt(st.nextToken()) * 2; // 내일로 티켓 금액

		adj = new int[nodeCnt][nodeCnt];
		railroAdj = new int[nodeCnt][nodeCnt];

		// 전체 도시
		city = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < nodeCnt; idx++) {
			String cityName = st.nextToken();
			// 같은 이름의 도시는 동일한 도시로 취급
			if (!city.containsKey(cityName)) city.put(cityName, idx);

			// 티켓 금액 초기화
			Arrays.fill(adj[idx], INF);
			Arrays.fill(railroAdj[idx], INF);
			adj[idx][idx] = 0;
			railroAdj[idx][idx] = 0;
		}

		// 여행 경로
		visitCityCnt = Integer.parseInt(br.readLine());
		route = new int[visitCityCnt];
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < visitCityCnt; idx++) {
			int cityIdx = city.get(st.nextToken());
			route[idx] = cityIdx;
		}

		// 교통수단별 티켓 금액
		edgeCnt = Integer.parseInt(br.readLine());
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			String transport = st.nextToken();
			int u = city.get(st.nextToken());
			int v = city.get(st.nextToken());
			int price = Integer.parseInt(st.nextToken()) * 2;

			// 기본 금액
			adj[u][v] = Math.min(adj[u][v], price);
			adj[v][u] = Math.min(adj[v][u], price);

			// 내일로 금액
			// 무궁화호, ITX-새마을, ITX-청춘 : 무료
			if (transport.equals("Mugunghwa") || transport.equals("ITX-Cheongchun") || transport.equals("ITX-Saemaeul")) {
				railroAdj[u][v] = 0;
				railroAdj[v][u] = 0;
			}
			// S-Train, V-Train : 50% 할인
			else if (transport.equals("S-Train") || transport.equals("V-Train")) {
				railroAdj[u][v] = Math.min(railroAdj[u][v], price / 2);
				railroAdj[v][u] = Math.min(railroAdj[v][u], price / 2);
			}
			// 할인 없음
			else {
				railroAdj[u][v] = Math.min(railroAdj[u][v], price);
				railroAdj[v][u] = Math.min(railroAdj[v][u], price);
			}
		}

		// 도시별 교통비
		for (int mid = 0; mid < nodeCnt; mid++) {
			for (int from = 0; from < nodeCnt; from++) {
				for (int to = 0; to < nodeCnt; to++) {
					adj[from][to] = Math.min(adj[from][to], adj[from][mid] + adj[mid][to]);
					railroAdj[from][to] = Math.min(railroAdj[from][to], railroAdj[from][mid] + railroAdj[mid][to]);
				}
			}
		}

		// 총 금액 계산
		int minBasicPrice = 0, minRailroPrice = 0;
		for (int idx = 0; idx < visitCityCnt - 1; idx++) {
			minBasicPrice += adj[route[idx]][route[idx + 1]];
			minRailroPrice += railroAdj[route[idx]][route[idx + 1]];
		}
		minRailroPrice += railroTicketPrice;

		if (minRailroPrice < minBasicPrice) {
			bw.write("Yes");
		} else {
			bw.write("No");
		}
		bw.flush();
		bw.close();
	}

}
