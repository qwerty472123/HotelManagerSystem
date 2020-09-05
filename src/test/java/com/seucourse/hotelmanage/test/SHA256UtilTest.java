package com.seucourse.hotelmanage.test;

import com.seucourse.hotelmanage.util.SHA256Util;
import org.junit.jupiter.api.Test;

public class SHA256UtilTest {
    @Test
    public void TestSHA256() {
        String pass = "123456";
        String pass1 = "123457";
        String passAfter = SHA256Util.getSHA256String(pass);
        String pass1After = SHA256Util.getSHA256String(pass1);
        System.out.println("123456加密后的字符串是：" + passAfter);
        System.out.println("123457加密后的字符串是：" + pass1After);
        System.out.println("123456两次加密后的字符串是：" + SHA256Util.getSHA256String(passAfter));
    }
}
