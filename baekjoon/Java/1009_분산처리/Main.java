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

	static int testCnt;

	public static void main(String[] args) throws IOException {
		testCnt = Integer.parseInt(br.readLine());
		while(testCnt-- > 0) {
			int a, b;
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			int result = 1;
			for(int idx=0; idx<b; idx++) {
				result = (result * a) % 10;
			}
			if (result == 0) result = 10;
			sb.append(result + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}