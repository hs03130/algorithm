import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int board[][];
	static int dist[][];
	static Deque<int[]> Q;
	
	static Vector<Integer> ans;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static final int X = 0;
	static final int Y = 1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		dist = new int[N][N];
		Q = new ArrayDeque<>();

		for (int row = 0; row < N; row++) {
			String line = br.readLine();
			for (int col = 0; col < N; col++) {
				board[row][col] = line.charAt(col) - '0' ;
			}
		}

		ans = new Vector<>();
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (board[row][col] == 1 && dist[row][col] == 0) {
					Q.push(new int[] {row, col});
					dist[row][col] = 1;
					int cnt = 1;
					while(!Q.isEmpty()) {
						int[] cur = Q.pop();
						for(int dir=0; dir<4; dir++) {
							int nx = cur[X] + dx[dir];
							int ny = cur[Y] + dy[dir];
							if(nx < 0 || nx >= N || ny <0 || ny >= N) continue;
							if(dist[nx][ny] == 1 || board[nx][ny] != 1) continue;
							dist[nx][ny] = 1;
							Q.push(new int [] {nx, ny});
							cnt++;
						}
					}
					ans.add(cnt);
				}
			}
		}
		
		Collections.sort(ans);
		System.out.println(ans.size());
		for(int idx=0; idx<ans.size(); idx++) {
			System.out.println(ans.get(idx) + " ");
		}

	}
}
