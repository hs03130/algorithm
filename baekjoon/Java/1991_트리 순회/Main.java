import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nodeCnt;
	static Node root;

	static void preorder(Node node) {
		sb.append(node.value);
		if (node.left != null) preorder(node.left);
		if (node.right != null) preorder(node.right);
	}

	static void inorder(Node node) {
		if (node.left != null) inorder(node.left);
		sb.append(node.value);
		if (node.right != null) inorder(node.right);
	}

	static void postorder(Node node) {
		if (node.left != null) postorder(node.left);
		if (node.right != null) postorder(node.right);
		sb.append(node.value);
	}

	static boolean addChild(Node cur, char target, char left, char right) {
		if (cur.value == target) {
			if (left != '.') {
				cur.left = makeNode(left);
			}
			if (right != '.') {
				cur.right = makeNode(right);
			}
			return true;
		}

		// value 노드를 찾는다
		if (cur.left != null && addChild(cur.left, target, left, right)) {
			return true;
		}
		if (cur.right != null && addChild(cur.right, target, left, right)) {
			return true;
		}
		return false;
	}

	static Node makeNode(char value) {
		if (value == '.') {
			return null;
		} else {
			return new Node(value);
		}
	}

	static class Node {
		char value;
		Node left, right;

		Node(char value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());
		// 루트 노드
		st = new StringTokenizer(br.readLine());
		root = new Node(st.nextToken().charAt(0));
		root.left = makeNode(st.nextToken().charAt(0));
		root.right = makeNode(st.nextToken().charAt(0));

		for (int idx = 1; idx < nodeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			char value = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			if (left == '.' && right == '.') continue;
			// 해당 노드를 찾아서 왼쪽, 오른쪽 자식 생성
			addChild(root, value, left, right);
		}

		// 전위 순회
		preorder(root);
		sb.append("\n");
		// 중위 순회
		inorder(root);
		sb.append("\n");
		// 후위 순회
		postorder(root);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
