package com.windseeker.aliyunuser.utils;

import java.security.MessageDigest;

/**
 * @author Windseeker
 * 提供md5加密
 */
public class Encrypt {
    private static final String PASSWORD_PREFIX = "windseeker";

    /**
     * 基础的md5加密
     *
     * @param inStr
     * @return
     */
    public static String encrypt(String inStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }

    /**
     *  用前缀+密码做md5
     * @param password
     * @return
     */
    public static String encryptPassword(String password){
        return encrypt(PASSWORD_PREFIX+password);
    }

}