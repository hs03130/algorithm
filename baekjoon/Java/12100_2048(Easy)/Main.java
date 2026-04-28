import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int boardSize;
	static int[][] board;
	static int ans = 2;

	static void bt(int k, int[][] arr) {
		if (k == 5) {
			for (int row = 0; row < boardSize; row++) {
				for (int col = 0; col < boardSize; col++) {
					if (arr[row][col] > ans) {
						ans = arr[row][col];
					}
				}
			}
			return;
		}

		for (int dir = 0; dir < 4; dir++) {
			bt(k + 1, move(arr, dir));
		}

	}

	public static void main(String[] args) throws IOException {
		boardSize = Integer.parseInt(br.readLine());
		board = new int[boardSize][boardSize];

		for (int row = 0; row < boardSize; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		bt(0, board);

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int[][] move(int[][] origin, int direction) {
		// 복사
		int[][] arr = new int[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			arr[row] = Arrays.copyOf(origin[row], boardSize);
		}

		// 오른쪽
		if (direction == 0) {
			for (int row = 0; row < boardSize; row++) {
				for (int col = boardSize - 1; col > 0; col--) {
					if (arr[row][col] == 0) {
						int k = 1;
						while (col - k >= 0) {
							if (arr[row][col - k] != 0) {
								arr[row][col] = arr[row][col - k];
								arr[row][col - k] = 0;
								break;
							}
							k++;
						}
					}
				}
			}
			for (int row = 0; row < boardSize; row++) {
				for (int col = boardSize - 1; col > 0; col--) {
					if (arr[row][col] == arr[row][col - 1]) {
						arr[row][col] *= 2;
						arr[row][col - 1] = 0;

					}
				}
			}
			for (int row = 0; row < boardSize; row++) {
				for (int col = boardSize - 1; col > 0; col--) {
					if (arr[row][col] == 0) {
						int k = 1;
						while (col - k >= 0) {
							if (arr[row][col - k] != 0) {
								arr[row][col] = arr[row][col - k];
								arr[row][col - k] = 0;
								break;
							}
							k++;
						}
					}
				}
			}
		}
		// 아래
		else if (direction == 3) {
			for (int col = 0; col < boardSize; col++) {
				for (int row = boardSize - 1; row > 0; row--) {
					if (arr[row][col] == 0) {
						int k = 1;
						while (row - k >= 0) {
							if (arr[row - k][col] != 0) {
								arr[row][col] = arr[row - k][col];
								arr[row - k][col] = 0;
								break;
							}
							k++;
						}
					}
				}
			}
			for (int col = 0; col < boardSize; col++) {
				for (int row = boardSize - 1; row > 0; row--) {
					if (arr[row][col] == arr[row - 1][col]) {
						arr[row][col] *= 2;
						arr[row - 1][col] = 0;
					}
				}
			}

			for (int col = 0; col < boardSize; col++) {
				for (int row = boardSize - 1; row > 0; row--) {
					if (arr[row][col] == 0) {
						int k = 1;
						while (row - k >= 0) {
							if (arr[row - k][col] != 0) {
								arr[row][col] = arr[row - k][col];
								arr[row - k][col] = 0;
								break;
							}
							k++;
						}
					}
				}
			}
		}
		// 왼쪽
		else if (direction == 1) {
			for (int row = 0; row < boardSize; row++) {
				for (int col = 0; col < boardSize - 1; col++) {
					if (arr[row][col] == 0) {
						int k = 1;
						while (col + k < boardSize) {
							if (arr[row][col + k] != 0) {
								arr[row][col] = arr[row][col + k];
								arr[row][col + k] = 0;
								break;
							}
							k++;
						}
					}
				}
			}
			for (int row = 0; row < boardSize; row++) {
				for (int col = 0; col < boardSize - 1; col++) {
					if (arr[row][col] == arr[row][col + 1]) {
						arr[row][col] *= 2;
						arr[row][col + 1] = 0;
					}
				}
			}
			for (int row = 0; row < boardSize; row++) {
				for (int col = 0; col < boardSize - 1; col++) {
					if (arr[row][col] == 0) {
						int k = 1;
						while (col + k < boardSize) {
							if (arr[row][col + k] != 0) {
								arr[row][col] = arr[row][col + k];
								arr[row][col + k] = 0;
								break;
							}
							k++;
						}
					}
				}
			}
		}
		// 위
		else if (direction == 2) {
			for (int col = 0; col < boardSize; col++) {
				for (int row = 0; row < boardSize - 1; row++) {
					if (arr[row][col] == 0) {
						int k = 1;
						while (row + k < boardSize) {
							if (arr[row + k][col] != 0) {
								arr[row][col] = arr[row + k][col];
								arr[row + k][col] = 0;
								break;
							}
							k++;
						}
					}
				}
			}
			for (int col = 0; col < boardSize; col++) {
				for (int row = 0; row < boardSize - 1; row++) {
					if (arr[row][col] == arr[row + 1][col]) {
						arr[row][col] *= 2;
						arr[row + 1][col] = 0;
					}
				}
			}
			for (int col = 0; col < boardSize; col++) {
				for (int row = 0; row < boardSize - 1; row++) {
					if (arr[row][col] == 0) {
						int k = 1;
						while (row + k < boardSize) {
							if (arr[row + k][col] != 0) {
								arr[row][col] = arr[row + k][col];
								arr[row + k][col] = 0;
								break;
							}
							k++;
						}
					}
				}
			}
		}

		return arr;
	}
}
