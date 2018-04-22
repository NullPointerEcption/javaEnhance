package d12ProxyPattern.jdkproxy;

public class TestImpl implements ITest {
    @Override
    public void test1() {
        System.out.println("i am Test1.");
    }

    @Override
    public void test2(String name) {
        System.out.println("i am Test2."+name);
    }
}
