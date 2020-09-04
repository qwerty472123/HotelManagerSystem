package com.seucourse.hotelmanage.util;

import java.security.NoSuchAlgorithmException;

public class PasswordEncryptUtil {
    private String salt = "hotelSystem";
    public String encrypt(String password) {
        return SHA256Util.getSHA256String(salt + SHA256Util.getSHA256String(password + salt));
    }
    public boolean check(String password, String pwdInDB) {
        return encrypt(password).equals(pwdInDB);
    }
}
