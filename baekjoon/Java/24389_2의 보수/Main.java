import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		int M = ~N + 1; // N의 2의 보수
		int diff = Integer.bitCount(N ^ M); // 서로 다르면 1, 같으면 0으로 바꾼 후 1의 개수 세기
		
		bw.write(String.valueOf(diff));
		bw.flush();
		bw.close();
	}

}