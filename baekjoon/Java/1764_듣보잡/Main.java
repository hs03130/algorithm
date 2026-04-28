import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int unheardOfCnt; // 듣도 못한 수
	static int invisibleCnt; // 보도 못한 수
	static HashSet<String> hs;
	static List<String> ans;
	
	public static void main(String[] args) throws Exception {
		ans = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		unheardOfCnt = Integer.parseInt(st.nextToken()); 
		invisibleCnt = Integer.parseInt(st.nextToken()); 

		hs = new HashSet<>();
		for(int idx=0; idx<unheardOfCnt; idx++) {
			hs.add(br.readLine());
		}   
		
		for(int idx=0; idx<invisibleCnt; idx++) {
			String target = br.readLine();
			if (hs.contains(target)) {
				ans.add(target);
			}
		}
		
		Collections.sort(ans);
		System.out.println(ans.size());
		for(int idx=0; idx<ans.size(); idx++) {
			System.out.println(ans.get(idx));
		}
	}

}