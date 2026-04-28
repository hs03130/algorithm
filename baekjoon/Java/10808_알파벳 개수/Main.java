import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static String word;
	static int[] alphabetCnt;

	public static void main(String[] args) throws Exception {
		word = br.readLine();
		
		alphabetCnt = new int[26];
		for (int idx = 0; idx < word.length(); idx++) {
			alphabetCnt[word.charAt(idx) - 'a']++;
		}
		
		for(int idx=0; idx<26; idx++) {
			System.out.print(alphabetCnt[idx] + " ");
		}
	}

}