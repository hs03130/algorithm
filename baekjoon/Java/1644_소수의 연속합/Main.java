import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int ans;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		if (N == 1) {
			ans = 0;
		} else if (N == 2) {
			ans = 1;
		} else {
			// N이하의 모든 소수
			boolean[] isComposite = new boolean[N + 1];
			for (int i = 2; i * i <= N; i++) {
				if (!isComposite[i]) {
					for (int j = i * i; j <= N; j += i) {
						isComposite[j] = true;
					}
				}
			}

			int primeCnt = 0;
			for (int idx = 2; idx <= N; idx++) {
				if (!isComposite[idx])
					primeCnt++;
			}

			int[] primes = new int[primeCnt];
			for (int num = 2, idx = 0; num <= N; num++) {
				if (!isComposite[num])
					primes[idx++] = num;
			}

			// 연속하는 소수의 합
			int left = 0, right = 1, sum = primes[0];
			while (right <= primeCnt) {
				if (sum == N) ans++;
				if (sum <= N) {
					if (right < primeCnt) sum += primes[right];
					right++;
				}
				if (sum > N) sum -= primes[left++];
			}
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
