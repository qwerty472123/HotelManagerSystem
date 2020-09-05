package com.seucourse.hotelmanage.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptUtil {
    private static String salt;
    @Value(value = "${password.salt}")
    public void setSalt(String val) {
        salt = val;
    }
    public static String encrypt(String password) {
        return SHA256Util.getSHA256String(salt + SHA256Util.getSHA256String(password + salt));
    }
    public static boolean check(String password, String pwdInDB) {
        if (password.equals(pwdInDB)) return true;
        return encrypt(password).equals(pwdInDB);
    }
}
