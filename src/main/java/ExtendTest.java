public class ExtendTest {
    private String name;
    public ExtendTest(String name) {
        this.name = name;
    }
}

class SubClass extends ExtendTest{
    public SubClass(String name) {
        super(name);
    }
}
