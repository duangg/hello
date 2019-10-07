import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class GuessServer {

    public static void main(String[] args) throws IOException {
        Socket server = null;
        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("Server start...");
            server = ss.accept();
            System.out.println("Accept connection" + server.getRemoteSocketAddress());
            PrintStream out = new PrintStream(server.getOutputStream());
            out.println(GuessConstants.BEGIN);
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            String res;
            Integer key = ThreadLocalRandom.current().nextInt(1, 101);
            System.out.println("KEY: " + key);
            while((res = in.readLine()) != null ) {
                System.out.println("Receive: " + res);
                Integer choice = Integer.parseInt(res);
                switch (choice) {
                    case GuessConstants.END:
                        System.out.println("end");
                        server.close();
                        break;
                    default:
                        if (choice > 0 && choice < 101) {
                            if (choice == key) {
                                System.out.println("Success");
                                out.println(GuessConstants.EQUAL);
                                System.out.println("Server shutdown");
                                out.println(GuessConstants.END);
                                server.close();
                                break;
                            } else if (choice > key) {
                                System.out.println("Greater than key");
                                out.println(GuessConstants.GREAT);
                            } else if (choice < key) {
                                System.out.println("Less than key");
                                out.println(GuessConstants.LESS);
                            }
                        } else {
                            System.out.println("Illegal signal");
                            out.println(GuessConstants.ILLEGAL);
                        }
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (server != null) {
                server.close();
            }
        }
    }
}
