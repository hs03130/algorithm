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
			// �̹� �����ϴ� ����
			if (count.containsKey(num)) {
				// �� ����
				count.put(num, count.get(num) + 1);
			}
			// ���� ���� ����
			else {
				// ���ڿ� �ε����� ����Ʈ�� �߰�
				nodes.add(new Node(num, idx));
				// �ʿ� ���� �߰�
				count.put(num, 1);
			}
		}

		nodes.sort((n1, n2) -> {
			// �󵵼��� ������ �ε����� ���� ���ڰ� �켱
			if (count.get(n1.number) == count.get(n2.number)) {
				return n1.index - n2.index;
			}
			// �󵵼��� ū ���ڰ� �켱
			return count.get(n2.number) - count.get(n1.number);
		});

		// �󵵼���ŭ ���� ���
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