import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
        
		int N = Integer.parseInt(br.readLine());

		for (int row = 0; row < N; row++) {
			for (int col = 0; col <= row; col++) {
				System.out.print('*');
			}
			System.out.println();
		}
	}

}
