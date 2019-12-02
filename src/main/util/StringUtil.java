package main.util;

public class StringUtil {
    public static boolean isEmpty(String str) {
        return "".equals(str)||str==null||"null".equals(str);
    }
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
