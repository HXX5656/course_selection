package main.util;
import java.sql.Connection;
import java.sql.DriverManager;
public class SqlUtil {
    private static Connection coonection =conn() ;
    private static boolean mutex =false;//互斥锁
    private static Connection conn() {
        try {
            //暂时先写死
            String url = "jdbc:mysql://localhost:3306/data?useUnicode=true&characterEncoding=UTF-8";
            String user="root";
            String pass="huxiao104628..";
            String driver="com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            return DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Connection createCon() {
        while (mutex) {
            try
            {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mutex=true;
        return coonection;
    }
    public static void closeCon() {
        mutex =false;
    }
}
