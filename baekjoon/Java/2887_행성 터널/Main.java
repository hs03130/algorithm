import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int nodeCnt, unionCnt = 0;
	static int[] parent;
	static Coordinate[] x, y, z;
	static PriorityQueue<Edge> pq;
	static int ans;

	static class Coordinate implements Comparable<Coordinate> {
		int coordinate, node;

		public Coordinate(int coordinate, int node) {
			this.coordinate = coordinate;
			this.node = node;
		}

		@Override
		public int compareTo(Coordinate o) {
			return Integer.compare(this.coordinate, o.coordinate);
		}
	}

	static class Edge implements Comparable<Edge> {
		int cost, u, v;

		public Edge(int cost, int u, int v) {
			this.cost = cost;
			this.u = u;
			this.v = v;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static int find(int node) {
		if (parent[node] < 0) return node;
		return parent[node] = find(parent[node]);
	}

	public static boolean isDiffGroup(int u, int v) {
		u = find(u);
		v = find(v);

		if (u == v) return false;

		if (parent[u] < parent[v]) { // 크기가 큰 집합으로 작은 집합을 합친다
			parent[u] += parent[v];
			parent[v] = u;
		} else {
			parent[v] += parent[u];
			parent[u] = v;
		}
		
		return true;
	}

	public static void main(String[] args) throws IOException {
		nodeCnt = Integer.parseInt(br.readLine());

		parent = new int[nodeCnt];
		Arrays.fill(parent, -1);

		x = new Coordinate[nodeCnt];
		y = new Coordinate[nodeCnt];
		z = new Coordinate[nodeCnt];

		// 행성 좌표
		for (int idx = 0; idx < nodeCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			x[idx] = new Coordinate(Integer.parseInt(st.nextToken()), idx);
			y[idx] = new Coordinate(Integer.parseInt(st.nextToken()), idx);
			z[idx] = new Coordinate(Integer.parseInt(st.nextToken()), idx);
		}

		Arrays.sort(x);
		Arrays.sort(y);
		Arrays.sort(z);

		// 인접한 두 행성의 비용
		pq = new PriorityQueue<>();
		for (int idx = 0; idx < nodeCnt - 1; idx++) {
			pq.offer(new Edge(Math.abs(x[idx].coordinate - x[idx + 1].coordinate), x[idx].node, x[idx + 1].node));
			pq.offer(new Edge(Math.abs(y[idx].coordinate - y[idx + 1].coordinate), y[idx].node, y[idx + 1].node));
			pq.offer(new Edge(Math.abs(z[idx].coordinate - z[idx + 1].coordinate), z[idx].node, z[idx + 1].node));
		}

		while (unionCnt < nodeCnt - 1) {
			Edge cur = pq.poll();
			if (!isDiffGroup(cur.u, cur.v)) continue;
			ans += cur.cost;
			unionCnt++;
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}

}
