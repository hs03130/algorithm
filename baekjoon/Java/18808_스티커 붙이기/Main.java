import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int boardH, boardW, stickerCnt;
	static int[][] board, sticker;
	static int stickerH, stickerW;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		boardH = Integer.parseInt(st.nextToken());
		boardW = Integer.parseInt(st.nextToken());
		stickerCnt = Integer.parseInt(st.nextToken());

		board = new int[boardH][boardW];

		for (int s = 0; s < stickerCnt; s++) {
			st = new StringTokenizer(br.readLine(), " ");
			stickerH = Integer.parseInt(st.nextToken());
			stickerW = Integer.parseInt(st.nextToken());
			sticker = new int[stickerH][stickerW];

			for (int row = 0; row < stickerH; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < stickerW; col++) {
					sticker[row][col] = Integer.parseInt(st.nextToken());
				}
			}

			notebook();
		}

		// ��ƼĿ ���ΰ� ����
		for (int row = 0; row < boardH; row++) {
			for (int col = 0; col < boardW; col++) {
				if (board[row][col] == 1)
					ans++;
			}
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void notebook() {
		for (int angle = 0; angle < 4; angle++) {

			// ��Ʈ�� ���� ������ ��ƼĿ ���� �� �ִ� �ڸ����� Ȯ��
			for (int startX = 0; startX <= boardH - stickerH; startX++) {
				for (int startY = 0; startY <= boardW - stickerW; startY++) {

					// ���� �� ������
					if (stick(startX, startY)) {
						// ��Ʈ�Ͽ� ���δ�
						for (int row = 0; row < stickerH; row++) {
							for (int col = 0; col < stickerW; col++) {
								if (sticker[row][col] == 1) {
									board[startX + row][startY + col] = 1;
								}
							}
						}
						return;
					}

				}
			}

			// �ð�������� 90�� ȸ��
			rotate();
		}
	}

	// ���� �� �ִ��� Ȯ��
	static boolean stick(int startX, int startY) {
		for (int row = 0; row < stickerH; row++) {
			for (int col = 0; col < stickerW; col++) {
				if (sticker[row][col] == 1 && board[startX + row][startY + col] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	// �ð�������� 90�� ȸ��
	static void rotate() {
		int[][] rotated = new int[stickerW][stickerH];

		for (int row = 0; row < stickerH; row++) {
			for (int col = 0; col < stickerW; col++) {
				rotated[col][stickerH - 1 - row] = sticker[row][col];
			}
		}

		int tmp = stickerH;
		stickerH = stickerW;
		stickerW = tmp;

		sticker = rotated;
	}
}
