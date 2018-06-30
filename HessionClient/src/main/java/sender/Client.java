package sender;

import com.caucho.hessian.client.HessianProxyFactory;
import interfaces.ICalculatable;

public class Client {
    public static void main(String[] args) throws Exception{
        String url = "http://localhost:8080/calc";
        HessianProxyFactory factory = new HessianProxyFactory();
        ICalculatable basic = (ICalculatable) factory.create(ICalculatable.class, url);
        System.out.println(basic.add(1, 2));
        System.out.println(basic.multi(23, 23));
    }
}
