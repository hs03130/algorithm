import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int treeH, up, down;

	static int ans;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());

		up = Integer.parseInt(st.nextToken());
		down = Integer.parseInt(st.nextToken());
		treeH = Integer.parseInt(st.nextToken());

		ans = (treeH - up) / (up - down);
		if ((treeH - up) % (up - down) > 0) {
			System.out.println(ans + 2);
		} else {
			System.out.println(ans + 1);
		}

	}

}
