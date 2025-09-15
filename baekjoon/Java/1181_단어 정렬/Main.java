import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static Vector<String> words = new Vector<>();
	static String preWord = "";

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		for (int idx = 0; idx < N; idx++) {
			words.add(br.readLine());
		}

		Collections.sort(words, new MyComparator());

		for (int idx = 0; idx < words.size(); idx++) {
			String word = words.get(idx);
			if (!word.equals(preWord)) {
				System.out.println(word);
			}
			preWord = word;
		}

	}

}

class MyComparator implements Comparator<String> {
	@Override
	public int compare(String a, String b) {
		if (a.length() < b.length())
			return -1;
		else if (a.length() > b.length())
			return 1;
		else {
			return a.compareTo(b);
		}
	}
}