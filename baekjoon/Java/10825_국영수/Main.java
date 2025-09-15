import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int studentCnt;
	static ArrayList<Node> students;

	public static void main(String[] args) throws IOException {
		studentCnt = Integer.parseInt(br.readLine());
		students = new ArrayList<>();
		for (int idx = 0; idx < studentCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			Node score = new Node(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
			students.add(score);
		}

		students.sort((s1, s2) -> {
			if (s1.ko != s2.ko) {
				return s2.ko - s1.ko;
			}
			if (s1.en != s2.en) {
				return s1.en - s2.en;
			}
			if (s1.math != s2.math) {
				return s2.math - s1.math;
			}
			return s1.name.compareTo(s2.name);
		});

		for (Node student : students) {
			sb.append(student.name + " \n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node {
		String name;
		int ko, en, math;

		Node(String name, int ko, int en, int math) {
			this.name = name;
			this.ko = ko;
			this.en = en;
			this.math = math;
		}
	}

}