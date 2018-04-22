package d11FlyWeightPattern.App2;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

public class App2 {
    public static void main(String[] args) {
        //region 创建配置信息
        PooledMyConnectionFactory fac = new PooledMyConnectionFactory();
        GenericObjectPoolConfig conf = new GenericObjectPoolConfig();
        conf.setMaxTotal(10);//最大连接数
        conf.setMaxIdle(3);//最大空闲连接数
        //endregion

        GenericObjectPool<MyConnection> pool = new GenericObjectPool<MyConnection>(fac, conf);

        for(int i=0;i<100;i++)
        {
            int data=i;
            Thread t = new Thread(new Runnable() {

                @Override
                public void run() {
                    MyConnection conn = null;
                    try {
                        conn = pool.borrowObject();
                        conn.send(""+data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally
                    {
                        pool.returnObject(conn);
                    }
                }
            });
            t.start();
        }
    }
    @Test
    public void test() throws Exception {


    }
}
