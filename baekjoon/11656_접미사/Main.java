import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static String line;
	static int len;
	static ArrayList<String> words;

	public static void main(String[] args) throws IOException {
		line = br.readLine();
		len = line.length();
		words = new ArrayList<>();
		for (int idx = 0; idx < len; idx++) {
			words.add(line.substring(idx, len));
		}

		Collections.sort(words);
		
		for (String word : words) {
			sb.append(word + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}