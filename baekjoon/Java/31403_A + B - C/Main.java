import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String A, B;
    static int C;

    public static void main(String[] args) throws Exception {
        A = br.readLine();
        B = br.readLine();
        C = Integer.parseInt(br.readLine());

        // 숫자(A) + 숫자(B) - C
        System.out.println(Integer.parseInt(A) + Integer.parseInt(B) - C);
        
        // 문자(A) + 문자(B) - C
        System.out.println(Integer.parseInt(A + B) - C);
    }
}