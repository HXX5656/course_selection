package main.util;

import java.sql.PreparedStatement;

public class StringUtil {
    public static boolean isEmpty(String str) {
        return "".equals(str)||str==null||"null".equals(str);
    }
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    public static void set_string(PreparedStatement ppst,int index,String arg,int types) {
        try {
            if(isEmpty(arg)) {
                ppst.setNull(index,types);
            }
            else {ppst.setString(index,arg);}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
