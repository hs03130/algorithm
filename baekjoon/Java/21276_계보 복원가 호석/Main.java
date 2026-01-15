import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt, edgeCnt;
	static String[] names;
	static HashMap<String, Integer> people;
	static int[] indegrees;
	static ArrayList<Integer>[] adjList, children;
	static ArrayDeque<Integer> Q;

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());
		names = new String[nodeCnt];
		people = new HashMap<>();
		indegrees = new int[nodeCnt];
		adjList = new ArrayList[nodeCnt];
		children = new ArrayList[nodeCnt];
		Q = new ArrayDeque<>();

		// 전체 사람 이름 입력
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < nodeCnt; idx++) {
			names[idx] = st.nextToken();
		}

		// 이름순으로 정렬하고, 인덱스 저장
		Arrays.sort(names);
		for (int idx = 0; idx < nodeCnt; idx++) {
			people.put(names[idx], idx);
		}

		// 조상 관계 입력
		edgeCnt = Integer.parseInt(br.readLine());
		int ancestorCnt = nodeCnt;
		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			int childIdx = people.get(st.nextToken());
			int parentIdx = people.get(st.nextToken());

			// 부모에 자식 추가
			if (adjList[parentIdx] == null) adjList[parentIdx] = new ArrayList<>();
			adjList[parentIdx].add(childIdx);

			// 자식의 indegree 증가
			if (indegrees[childIdx] == 0) ancestorCnt--;
			indegrees[childIdx]++;
		}

		// 각 가문은 한 명의 시조를 가지므로 가문의 개수는 시조 수와 같음
		sb.append(ancestorCnt + "\n");

		// 시조(indegree가 0인 사람)를 큐에 추가하고, 이름 출력
		for (int idx = 0; idx < nodeCnt; idx++) {
			if (indegrees[idx] == 0) {
				Q.offer(idx);
				sb.append(names[idx] + " ");
			}
		}
		sb.append("\n");

		// 직계 자손 확인
		while (!Q.isEmpty()) {
			int cur = Q.poll();
			if (adjList[cur] == null) continue;
			for (int next : adjList[cur]) {
				indegrees[next]--;
				
				if (indegrees[next] == 0) {
					Q.offer(next);

					if (children[cur] == null) children[cur] = new ArrayList<>();
					children[cur].add(next); // next는 cur의 자식
				}
			}
		}

		for (int idx = 0; idx < nodeCnt; idx++) {
			// 사전순 대로 이름 출력
			sb.append(names[idx] + " ");
			
			// 자식의 수 출력
			if (children[idx] == null) {
				sb.append("0\n");
				continue;
			}
			sb.append(children[idx].size());
			
			// 사전순으로 자식 이름 출력
			Collections.sort(children[idx]);
			for (int child : children[idx]) {
				sb.append(" " + names[child]);
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
