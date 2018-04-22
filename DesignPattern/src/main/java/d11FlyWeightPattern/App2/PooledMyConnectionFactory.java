package d11FlyWeightPattern.App2;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class PooledMyConnectionFactory implements PooledObjectFactory<MyConnection> {
    @Override
    public PooledObject<MyConnection> makeObject() throws Exception {
        System.out.println("开始创建新的MyConnection对象");
        MyConnection conn = new MyConnection();
        System.out.println("完成创建新的MyConnection对象");
        return new DefaultPooledObject<MyConnection>(conn);
    }

    @Override
    public void destroyObject(PooledObject<MyConnection> pooledObject) throws Exception {
        pooledObject.getObject().close();
    }

    @Override
    public boolean validateObject(PooledObject<MyConnection> pooledObject) {
        return pooledObject.getObject().isClosed()==false;
    }

    @Override
    public void activateObject(PooledObject<MyConnection> pooledObject) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<MyConnection> pooledObject) throws Exception {

    }
}
