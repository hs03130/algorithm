import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb;

	static int pokemonCnt;
	static int problemCnt;
	static HashMap<String, Integer> hm;
	static String[] pokemons;
	
	public static void main(String[] args) throws Exception {
		sb = new StringBuilder();

		
		st = new StringTokenizer(br.readLine());
		pokemonCnt = Integer.parseInt(st.nextToken()); 
		problemCnt = Integer.parseInt(st.nextToken()); 
		
		// 도감 등록
		hm = new HashMap<>();
		pokemons = new String[pokemonCnt+1];
		for(int idx=1; idx<=pokemonCnt; idx++) {
			pokemons[idx] = br.readLine();
			hm.put(pokemons[idx], idx);
		}
		
		for(int cnt=0; cnt<problemCnt; cnt++) {
			String target = br.readLine();
			if (hm.containsKey(target)) {
				sb.append(hm.get(target) + "\n");
			} else {
				sb.append(pokemons[Integer.parseInt(target)] + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}