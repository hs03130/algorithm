/*
 * M*N�� ������ �ִ�.
 * ������ ������ �˷��ָ�
 * ������ ������ �����ϰ� ������ �κ��� ������ ������ ���̸� �Է��Ѵ�.
 * 
 * 1. �Է�
 *  1-1 �� ũ�� | �� ũ�� | ���� ����
 *  1-1 ���� ��ǥ (���ʾƷ� x,y | ������ �� x,y)
 *  
 * 2. �� ���� ���ϱ�
 *  2-1 �Է��� �����鼭 ������ ���� ������ ǥ���Ѵ�.
 *  2-2 ���� ������ �ݺ��ϸ鼭 ���� ���� �κ��� ������
 *      �� �κ��� ���������� ������ ���Ѵ�. (BFS)
 *  2-3 ��������, ���� ����
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int rowSize;
	static int colSize;
	static int iceCnt;
	static int[][] map;

	static int cnt;
	static List<Integer> sizeList;

	static Deque<int[]> Q;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int X = 0;
	static int Y = 1;

	// �Է�
	static void input() throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		iceCnt = Integer.parseInt(st.nextToken());

		// ���� ���� ǥ��
		map = new int[rowSize][colSize];
		for (int ice = 0; ice < iceCnt; ice++) {
			st = new StringTokenizer(br.readLine().trim());
			int leftY = Integer.parseInt(st.nextToken());
			int leftX = Integer.parseInt(st.nextToken());
			int rightY = Integer.parseInt(st.nextToken());
			int rightX = Integer.parseInt(st.nextToken());
			for (int row = leftX; row < rightX; row++) {
				for (int col = leftY; col < rightY; col++) {
					map[row][col] = 1;
				}
			}
		}
	}

	// �� ����, ���� ���ϱ�
	static void solve() {
		cnt = 0;
		sizeList = new ArrayList<>(); // ��������
		Q = new ArrayDeque<>();

		// ������ ��ȸ�ϸ鼭 ������ �ƴ� ������ ã��
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (map[row][col] == 0) {
					// ������
					int size = 0; // ����
					
					Q.offer(new int[] { row, col });
					map[row][col] = 1;
					while (!Q.isEmpty()) {
						int[] cur = Q.poll();
						size++;
						for (int dir = 0; dir < 4; dir++) {
							int nx = cur[X] + dx[dir];
							int ny = cur[Y] + dy[dir];
							// ���� ���� ��������
							if (nx < 0 || nx >= rowSize || ny < 0 || ny >= colSize) {
								continue;
							}
							// �����̰ų� �̹� Ȯ���� �� ĭ
							if (map[nx][ny] == 1) {
								continue;
							}
							// �� ���� ���� ����
							map[nx][ny] = 1;
							Q.offer(new int[] { nx, ny });
						}
					}
					cnt++; // �� ���� ���� ����
					sizeList.add(size); // ���� �߰�
				}
			}
		}
		// ���� ������������
		Collections.sort(sizeList);
	}

	public static void main(String[] args) throws Exception {
		input();
		solve();

		// �� ����
		System.out.println(cnt);
		// �� ������ ���� (��������)
		for (int size : sizeList) {
			System.out.print(size + " ");
		}
	}
}
