package com.seucourse.hotelmanage.util;

import java.security.NoSuchAlgorithmException;

public class PasswordEncryptUtil {
    private static String salt = "hotelSystem";
    public static String encrypt(String password) {
        return SHA256Util.getSHA256String(salt + SHA256Util.getSHA256String(password + salt));
    }
    public static boolean check(String password, String pwdInDB) {
        if (password.equals(pwdInDB)) return true;
        return encrypt(password).equals(pwdInDB);
    }
}
