import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static String ans = "DCBAE";
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        for(int i=0;i<3;i++) {
        	int sum=0;
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int j=0;j<4;j++) {
        		int num = Integer.parseInt(st.nextToken());
        		sum += num;
        	}
        	bw.write(ans.charAt(sum));
        	bw.newLine();
        }
        
        bw.flush();
        bw.close();
	}
}
