import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, C;
	static ArrayList<Node> nodes;
	static HashMap<Integer, Integer> count;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		nodes = new ArrayList<>();
		count = new HashMap<>();

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			int num = Integer.parseInt(st.nextToken());
			// 이미 존재하는 숫자
			if (count.containsKey(num)) {
				// 빈도 증가
				count.put(num, count.get(num) + 1);
			}
			// 새로 나온 숫자
			else {
				// 숫자와 인덱스를 리스트에 추가
				nodes.add(new Node(num, idx));
				// 맵에 숫자 추가
				count.put(num, 1);
			}
		}

		nodes.sort((n1, n2) -> {
			// 빈도수가 같으면 인덱스가 작은 숫자가 우선
			if (count.get(n1.number) == count.get(n2.number)) {
				return n1.index - n2.index;
			}
			// 빈도수가 큰 숫자가 우선
			return count.get(n2.number) - count.get(n1.number);
		});

		// 빈도수만큼 숫자 출력
		for (Node node : nodes) {
			int c = count.get(node.number);
			while (c-- > 0) {
				sb.append(node.number + " ");
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node {
		int number;
		int index;

		Node(int number, int index) {
			this.number = number;
			this.index = index;
		}
	}

}