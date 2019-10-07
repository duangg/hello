public class ThreadTest {
    public static void main(String[] args) {
        Thread t = new Thread(() -> System.out.println("hello"), "greeting");
        t.start();
    }
}
