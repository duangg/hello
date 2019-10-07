import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOTest {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        char c;
        String s;
        do {
            s = bf.readLine();
            System.out.println(s);
        } while (!s.equals("end"));
    }
}
