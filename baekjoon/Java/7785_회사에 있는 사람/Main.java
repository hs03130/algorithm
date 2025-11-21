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

	static int personCnt;
	static HashMap<String, Boolean> map;
	static ArrayList<String> people;

	public static void main(String[] args) throws IOException {
		personCnt = Integer.parseInt(br.readLine());
		map = new HashMap<>();
		for (int idx = 0; idx < personCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			if (st.nextToken().equals("enter")) {
				map.put(name, true);
			} else {
				map.remove(name);
			}
		}

		people = new ArrayList<>();
		for (String name : map.keySet()) {
			people.add(name);
		}
		people.sort((n1, n2) -> {
			return -n1.compareTo(n2);
		});
		for (String name : people) {
			sb.append(name + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
