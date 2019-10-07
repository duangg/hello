import java.io.*;

public class SerializeTest {
    public static void main(String[] args) {
        Employ e = new Employ("hah");
        try {
            FileOutputStream fos = new FileOutputStream("test.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(e);
            oos.close();
            fos.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream("test.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Employ e1 = (Employ) ois.readObject();
            ois.close();
            fis.close();
            System.out.println(e1.name);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

class Employ implements Serializable{
    private static final long serialVersionUID = -1956507178347707940L;
    public String name;
    public Employ(String name) {
        this.name = name;
    }
}