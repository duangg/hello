public class AbstractTest {
}
interface Animal {
    static void shout() {
        System.out.println("HAHA");
    }

    default void eat() {
        System.out.println("Eating");
    }
}

abstract class AnimalFactory implements Animal {
    public void eat() {
        System.out.println("Eating ...");
    }
}