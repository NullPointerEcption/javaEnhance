package d1MethodTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Author: wangyufei
 * CreateTime:2018/03/03
 * Companion:Champion Software
 */
public class MySQLReader extends DBReader{
    private static final String url = "jdbc:mysql://localhost:3306/tysql?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT";
    @Override
    protected Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("数据库驱动加载异常"+e);
        }

        try {
            Connection connection = DriverManager.getConnection(url,"root","root");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("数据库连接异常"+e);
        }
    }

    @Override
    protected void processResultSet(ResultSet rs) {
        try {
            StringBuilder rowVal = new StringBuilder();
            while (rs.next()){
                int row = rs.getRow();
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    rowVal.append("row:").append(row).append(rs.getObject(i)) ;
                }
                rowVal.append("\r\n");
            }
            System.out.println(rowVal);//测试代码随便写了
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MySQLReader reader=new MySQLReader();
        reader.executeQuery("select * from customers");
    }
}
