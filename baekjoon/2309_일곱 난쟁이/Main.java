import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] arr = new int[9];
		int total = 0;
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
			total += arr[i];
		}
		Arrays.sort(arr);
		
		for (int i = 0; i < 8; i++) {
			for (int j = i + 1; j < 9; j++) {
				int tmp = total - arr[i] - arr[j];
				if (tmp == 100) {
					for (int n = 0; n < 9; n++) {
						if (n == i || n == j) continue;
						System.out.println(arr[n]);
					}
					return;
				}
			}
		}
        
	}
    
}