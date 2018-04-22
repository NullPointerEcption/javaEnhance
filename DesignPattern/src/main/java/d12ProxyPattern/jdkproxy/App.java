package d12ProxyPattern.jdkproxy;

import org.junit.Test;

public class App {

    @Test
    public void test() throws Exception {
        ITest test= (ITest) new TestJDKProxy().bind(new TestImpl());
        test.test1();
        test.test2("hello");
    }

}

