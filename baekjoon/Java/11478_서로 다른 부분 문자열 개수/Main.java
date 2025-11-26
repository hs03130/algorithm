import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static String word;
	static HashSet<String> set;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		word = br.readLine();
		set = new HashSet<>();

		for (int row = 0; row < word.length(); row++) {
			for (int col = row + 1; col <= word.length(); col++) {
				set.add(word.substring(row, col));
			}
		}

		ans = set.size();
		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
