import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeSet;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[] heights;
	static TreeSet<Integer> tree;
	static long ans = 0L;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		heights = new int[N]; // 0 ~ N-1
		tree = new TreeSet<>();

		for (int idx = 0; idx < N; idx++) {
			int num = Integer.parseInt(br.readLine());

			Integer big = tree.higher(num);
			Integer small = tree.lower(num);

			heights[num] = Math.max(big != null ? heights[big] : 0, small != null ? heights[small] : 0) + 1;
			ans += heights[num];

			tree.add(num);
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
