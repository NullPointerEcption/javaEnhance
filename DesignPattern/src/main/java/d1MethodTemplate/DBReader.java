package d1MethodTemplate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: wangyufei
 * CreateTime:2018/03/03
 * Companion:Champion Software
 */
public abstract class DBReader {
    /**
     * 创建一个连接 交给对应的类去实现
     * @return
     */
    protected abstract Connection createConnection();

    /**
     * 处理对应的ResultSet 交给对应的类去实现
     * @param rs
     */
    protected  abstract void processResultSet(ResultSet rs);

    /**
     * 关闭资源的方法
     * @param c
     */
    protected void closeQuietly(AutoCloseable c){
        if(c!=null){
            try {
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 执行查询方法
     * @param sql 要执行的sql
     * @param params 参数
     */
    protected void executeQuery(String sql,Object...params){
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs= null;
        try{
            connection=createConnection();
            ps = connection.prepareStatement(sql);
            if(params!=null&&params.length>0){
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i+1,params[i]);
                }
            }
            rs = ps.executeQuery();
            //process resultset
            processResultSet(rs);//执行用户要进行的操作

        }catch (SQLException ex){

        }finally {
            closeQuietly(rs);
            closeQuietly(ps);
            closeQuietly(connection);

        }
    }

}
