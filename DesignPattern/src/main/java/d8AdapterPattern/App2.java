package d8AdapterPattern;

import org.junit.Test;

/**
 * 假如有一个Computer类有close方法，但是没有实现AutoClosable接口，在不改变Computer类的情况下怎么样能使用try…with..resource
 */
public class App2 {

    @Test
    public void test() throws Exception {

        //通过适配器模式变相的close
        Computer computer = new Computer();
        try (
                ComputerAdapter adapter = new ComputerAdapter(computer);
        ) {
            computer.doSomething();
        }

    }

}

class Computer {


    public void doSomething() {
        System.out.println("i just do something ...");
    }

    public void close() {
        System.out.println("do something like close resource.");
    }

}

class ComputerAdapter implements AutoCloseable {
    private Computer compute;

    public ComputerAdapter(Computer compute) {
        this.compute = compute;
    }

    @Override
    public void close() {
        compute.close();
    }
}
