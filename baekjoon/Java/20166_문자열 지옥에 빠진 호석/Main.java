import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int boardH, boardW, wordCnt;
	static char[][] board;
	static HashMap<String, Integer> map;

	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static void dfs(int x, int y, String word) {
		map.put(word, map.getOrDefault(word, 0) + 1);

		if (word.length() == 5) {
			return;
		}

		for (int dir = 0; dir < 8; dir++) {
			int nx = (x + dx[dir] + boardH) % boardH;
			int ny = (y + dy[dir] + boardW) % boardW;
			dfs(nx, ny, word + board[nx][ny]);
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());
		wordCnt = Integer.parseInt(st.nextToken());

		board = new char[boardH][boardW];
		for (int row = 0; row < boardH; row++) {
			board[row] = br.readLine().toCharArray();
		}

		map = new HashMap<>();
		for (int row = 0; row < boardH; row++) {
			for (int col = 0; col < boardW; col++) {
				dfs(row, col, String.valueOf(board[row][col]));
			}
		}

		for (int idx = 0; idx < wordCnt; idx++) {
			String word = br.readLine();
			sb.append(map.getOrDefault(word, 0) + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
