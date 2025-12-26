import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int stationCnt, edgeCnt, hyperTubeCnt;
	static ArrayList<Integer>[] stationAdjList, hyperTubeAdjList;
	static int[] stationVisited;
	static boolean[] hyperTubeVisited;
	static ArrayDeque<Integer> Q;
	static int ans = -1;

	static void bfs() {
		while (!Q.isEmpty()) {
			int cur = Q.poll();
			if (stationAdjList[cur] == null) continue;
			// cur역이 포함된 하이퍼튜브
			for (int hyperTube : stationAdjList[cur]) {
				if (hyperTubeVisited[hyperTube]) continue;
				// 하이퍼튜브에 포함된 역들
				for (int nextStation : hyperTubeAdjList[hyperTube]) {
					if (nextStation == cur || stationVisited[nextStation] >= stationVisited[cur])
						continue;

					stationVisited[nextStation] = stationVisited[cur] + 1;
					if (nextStation == stationCnt - 1) {
						ans = stationVisited[nextStation];
						return;
					}
					Q.offer(nextStation);
				}
				hyperTubeVisited[hyperTube] = true;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		stationCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());
		hyperTubeCnt = Integer.parseInt(st.nextToken());

		stationAdjList = new ArrayList[stationCnt];
		hyperTubeAdjList = new ArrayList[hyperTubeCnt];
		for (int hyperTubeIdx = 0; hyperTubeIdx < hyperTubeCnt; hyperTubeIdx++) {
			st = new StringTokenizer(br.readLine());
			hyperTubeAdjList[hyperTubeIdx] = new ArrayList<>();
			for (int edgeIdx = 0; edgeIdx < edgeCnt; edgeIdx++) {
				int station = Integer.parseInt(st.nextToken()) - 1;
				hyperTubeAdjList[hyperTubeIdx].add(station);
				if (stationAdjList[station] == null) {
					stationAdjList[station] = new ArrayList<>();
				}
				stationAdjList[station].add(hyperTubeIdx);
			}
		}

		stationVisited = new int[stationCnt];
		hyperTubeVisited = new boolean[hyperTubeCnt];
		Q = new ArrayDeque<>();

		stationVisited[0] = 1;
		Q.offer(0);
		bfs();

		if (stationCnt == 1) ans = 1;
		bw.write(Integer.toString(ans));
		bw.flush();
		bw.close();
	}

}
