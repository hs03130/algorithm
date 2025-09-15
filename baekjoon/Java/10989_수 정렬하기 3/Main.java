import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int N;
	static int nums[] = new int[10001];

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		for (int idx = 0; idx < N; idx++) {
			nums[Integer.parseInt(br.readLine())]++;
		}

		for (int idx=1; idx<= 10000; idx++) {
			while (nums[idx]-- > 0) {
				bw.write(idx + "\n");
			}
		}

		bw.flush();
	}

}
