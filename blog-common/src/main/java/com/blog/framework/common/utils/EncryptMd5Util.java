package com.blog.framework.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;

/**
 * @author liuzw
 */
@Slf4j
public class EncryptMd5Util {

    private final static String[] HEXDIGITS =
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String getMD5(String value) {
        String result = null;

        if (value == null) {
            return null;
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] results = md.digest(value.getBytes());
            String resultString = byteArrayToHexString(results);
            result = resultString.toLowerCase();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }


        return result;

    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte b1 : b) {
            resultSb.append(byteToHexString(b1));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEXDIGITS[d1] + HEXDIGITS[d2];
    }

    public static void main(String[] args) {
        String password = "12345";
        System.out.println("MD5 of " + password + ":\n" + EncryptMd5Util.getMD5(password));

    }
}
