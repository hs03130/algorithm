
/**
 * 1. 4등분하여 타겟이 어느 칸에 있는지 확인
 * 2. 다른 칸은 길이를 곱해서 바로 계산하고, 타겟에 해당하는 칸만 다시 재귀적으로 계산
 * 3. len은 최대 2^15로 int 범위에서 가능
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, r, c;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		System.out.println(func(N, r, c));
	}

	static int func(int len, int row, int col) {

		if (len == 0) {
			return 0;
		}

		int half = 1 << (len - 1); // 2^(len-1)

		if (row < half && col < half) {
			return func(len - 1, row, col);
		} else if (row < half && col >= half) {
			return half * half + func(len - 1, row, col - half);
		} else if (row >= half && col < half) {
			return 2 * half * half + func(len - 1, row - half, col);
		} else {
			return 3 * half * half + func(len - 1, row - half, col - half);
		}

	}
}
