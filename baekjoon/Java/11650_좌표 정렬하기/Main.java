import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[][] coordi;

	static final int X = 0;
	static final int Y = 1;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		coordi = new int[N][2];
		for (int idx = 0; idx < N; idx++) {
			st = new StringTokenizer(br.readLine());
			coordi[idx][X] = Integer.parseInt(st.nextToken());
			coordi[idx][Y] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(coordi, new Comparator<int[]>() {
			public int compare(int[] c1, int[] c2) {
				if (c1[X] == c2[X]) {
					return c1[Y] - c2[Y];
				} else {
					return c1[X] - c2[X];
				}
			}
		});

		for (int idx = 0; idx < N; idx++) {
			System.out.println(coordi[idx][X] + " " + coordi[idx][Y]);
		}
	}

}
