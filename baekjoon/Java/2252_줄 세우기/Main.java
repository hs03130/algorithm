import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 학생 수와, 비교한 키 개수가 주어졌을 때
 * 학생 키를 기준으로 위상 정렬
 * 
 * 1. 입력
 *  1-1 학생 수 | 키 비교 횟수
 *  1-2 비교한 키 정보 (앞에 서는 학생 | 뒤에 서는 학생)
 * 
 * 2. 키 위상 정렬 (BFS)
 *  2-1 진입 간선이 없는 학생을 큐에 넣는다.
 *  2-2 큐에서 하나씩 꺼내서 인접한 간선을 삭제한다.
 *  2-3 삭제한 간선의 정점도 진입 간선이 0이 되면 큐에 넣는다.
 *  2-4 큐가 빌 때까지 위 작업을 반복한다. (싸이클 없음)
 */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb;

	static int vertexCnt;
	static int edgeCnt;
	static Node[] adjOutList;
	static Node[] adjInList;

	static Deque<Integer> Q;

	// 입력
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		vertexCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		adjOutList = new Node[vertexCnt + 1];
		adjInList = new Node[vertexCnt + 1];

		// 간선 입력
		for (int edge = 0; edge < edgeCnt; edge++) {
			st = new StringTokenizer(br.readLine().trim());
			int fromNo = Integer.parseInt(st.nextToken());
			int toNo = Integer.parseInt(st.nextToken());
			adjOutList[fromNo] = new Node(toNo, adjOutList[fromNo]);
			adjInList[toNo] = new Node(fromNo, adjInList[toNo]);
		}
	}

	// 위상 정렬
	static void solve() {
		sb = new StringBuilder();
		Q = new ArrayDeque<>();
		
		// 진입 간선이 0개인 정점을 큐에 넣는다.
		for (int vertex = 1; vertex <= vertexCnt; vertex++) {
			if (adjInList[vertex] == null) {
				Q.offer(vertex);
			}
		}
		// 큐가 빌때까지 작업을 반복한다.
		while(!Q.isEmpty()) {
			// 큐에서 정점을 꺼낸다.
			int vertexNo = Q.poll();
			sb.append(vertexNo + " ");
			
			// 큐의 간선을 하나씩 삭제한다.
			Node target = adjOutList[vertexNo];
			while (target != null) {
				if (adjOutList[vertexNo] != null) { removeOut(vertexNo, target.no); };
				if (adjInList[target.no] != null) { removeIn(vertexNo, target.no); };
				
				// 간선을 삭제한 인접 정점의 진입 간선이 0개가 되었다면 큐에 넣기
				if (adjInList[target.no] == null) {
					Q.offer(target.no);
				}
				
				target = target.next;
			}
		}
	}
	
	// 진출 간선 제거하기
	static void removeOut(int fromVertex, int toVertex) {
		Node target = adjOutList[fromVertex];
		if (target.no == toVertex) { // 첫 번째
			adjOutList[fromVertex] = target.next;  // 삭제
		}
		else { // 두 번째 이후~
			// 삭제할 노드 직전 노드 찾기
			while (target.next != null) {
				if (target.next.no == toVertex) {
					target.next = target.next.next; // 삭제
					return;
				}
				target = target.next;
			}
		}
	}
	// 진입 간선 제거하기
	static void removeIn(int fromVertex, int toVertex) {
		Node target = adjInList[toVertex];
		if (target.no == fromVertex) {  // 첫 번째
			adjInList[toVertex] = target.next;  // 삭제
		} 
		else { // 두 번째 이후~
			// 삭제할 노드 직전 노드 찾기
			while(target.next != null) {
				if (target.next.no == fromVertex) {
					target.next = target.next.next; // 삭제
					return;
				}
				target = target.next;
			}
		}
	}

	static class Node {
		int no;
		Node next;

		Node(int no, Node next) {
			this.no = no;
			this.next = next;
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(sb.toString());

	}

}