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

	static String line;
	static int len;
	static int[] cnt;
	static int ans;

	public static void main(String[] args) throws IOException {
		line = br.readLine();
		len = line.length();
		cnt = new int[2];
		char ch, tmp = line.charAt(0);

		for (int idx = 0; idx < len; idx++) {
			ch = line.charAt(idx);
			if (ch != tmp) {
				cnt[tmp - '0']++;
				tmp = ch;
			}
		}
		cnt[line.charAt(len - 1) - '0']++;

		sb.append(Math.min(cnt[0], cnt[1]));

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}