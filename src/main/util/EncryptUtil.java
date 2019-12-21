package main.util;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptUtil {
    public static String md5(String content, String salt) {
        return DigestUtils.md5Hex(content + salt);
    }
    public static boolean verify(String content, String salt, String md5) {
        return md5(content, salt).equalsIgnoreCase(md5);
    }
}
