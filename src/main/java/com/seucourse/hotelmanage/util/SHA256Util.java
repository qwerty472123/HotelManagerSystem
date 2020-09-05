package com.seucourse.hotelmanage.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Util {

    public static String getSHA256String(String password) {
        char[] hexCharacters = "0123456789abcdef".toCharArray();
        byte[] passBytes = password.getBytes();
        MessageDigest mdInstance;
        try {
            mdInstance = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
        mdInstance.update(passBytes);
        byte[] afterBytes = mdInstance.digest();
        char[] resultChars = new char[afterBytes.length * 2];
        int k = 0;
        for (byte temp : afterBytes) {
            resultChars[k++] = hexCharacters[temp >>> 4 & 0xf];
            resultChars[k++] = hexCharacters[temp & 0xf];
        }
        return new String(resultChars).toUpperCase();
    }
}
