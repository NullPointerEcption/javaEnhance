package d12ProxyPattern.cglib;

import org.junit.Test;

public class App {

    @Test
    public void test() throws Exception {
        TestImpl test= (TestImpl) new TestCGLibProxy().getInstance(new TestImpl());
        test.test1();
        test.test2("hi");
    }
}
