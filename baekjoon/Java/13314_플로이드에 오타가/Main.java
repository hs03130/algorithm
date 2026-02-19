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

	static int N = 100;

	public static void main(String[] args) throws IOException {
		sb.append(N + "\n");
		for (int from = 1; from <= N; from++) {
			for (int to = 1; to <= N; to++) {
				if (from == to) sb.append("0 ");
				else if (from == N || to == N) sb.append("1 ");
				else sb.append("3 ");
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}

/*
 * 지구이의 잘못된 코드에서 D의 최단 경로를 구할때 
 * k < N으로 인해 N번 정점을 경유하는 경우를 고려하지 않고 있다.
 *  
 * 따라서 from -> to로 이동할 때 N을 경유하는 것이 더 빠른 경우 틀린 쌍이 된다. 
 * 즉, adj[from][to] > adj[from][N] + adj[N][to]이면 틀린 쌍이 된다.
 * 
 * 위 코드에서는 임의로 일반 간선의 가중치는 3으로 두고, 
 * N과 연결된 간선(adj[from][N], adj[N][to])은 1로 두었다.
 * 
 * 1. from == to인 경우 0으로 동일하다. 
 * 2. N이 포함된 행/열은 틀린 코드와 맞는 코드에 차이가 없다. 
 * 
 * 틀린 쌍이 9700개 이상이 되려면 N은 최소 100이어야 한다.
 * 총 데이터 10000에서 1과 2의 경우를 제외하면 9702개의 틀린 쌍이 나온다.
 */