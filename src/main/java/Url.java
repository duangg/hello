import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

public class Url {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://www.baidu.com");
        BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
        BufferedWriter wf = new BufferedWriter(new FileWriter("page.html"));
        String line;
        while ((line = bf.readLine()) != null) {
            wf.write(line);
            wf.newLine();
        }
        bf.close();
        wf.close();
    }
}
