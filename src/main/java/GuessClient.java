import java.io.*;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class GuessClient {
    public static final void main(String[] args) throws IOException{
        Socket client = null;
        try {
            client = new Socket("127.0.0.1", 8888);
            System.out.println("connect to 127.0.0.1:8888");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintStream out = new PrintStream(client.getOutputStream());
            int min = 1;
            int max = 101;
            int key = (min + max -1) /2;
            int signal;
            String s = null;
            while ((s = in.readLine()) != null) {
                System.out.println("Receive: " + s);
                signal = Integer.parseInt(s);
                switch (signal) {
                    case GuessConstants.BEGIN:
                        key = (min + max -1) /2;
                        System.out.println("Guess key " + String.valueOf(key));
                        out.println(key);
                        break;
                    case GuessConstants.END:
                        System.out.println("end");
                        client.close();
                        break;
                    case GuessConstants.EQUAL:
                        System.out.println("EQUAL!");
                        out.println(GuessConstants.END);
                        System.out.println("end");
                        client.close();
                        break;
                    case GuessConstants.GREAT:
                        max = key;
                        key = (min + max -1) /2;
                        System.out.println("Guess key " + String.valueOf(key));
                        out.println(key);
                        break;
                    case GuessConstants.LESS:
                        min = key + 1;
                        key = (min + max -1) /2;
                        System.out.println("Guess key " + String.valueOf(key));
                        out.println(key);
                        break;
                    default:
                        System.out.println("Not recognize");
                        Thread.sleep(1000L);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (InterruptedException  exception) {
            exception.printStackTrace();
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
}
