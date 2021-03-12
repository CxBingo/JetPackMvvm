package com.bingo.basemodule.network.utils;

import androidx.annotation.NonNull;

import java.nio.charset.Charset;
import java.security.MessageDigest;

public class MD5util {

    static final Charset UTF_8 = Charset.forName("UTF-8");

    /**
     * @param buffer
     * @return
     */
    public static String getMd5String(byte[] buffer) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMd5String(@NonNull String source) {
        return getMd5String(source.getBytes(UTF_8));
    }

}
