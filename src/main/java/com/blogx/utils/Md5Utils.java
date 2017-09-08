package com.blogx.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * author： xueyuan
 * date  ： 2017-09-08 上午11:01
 */
public class Md5Utils {


    /**
     * 普通md5 加密
     *
     * @param str
     * @return
     */
    public static String md5Encode(String str) {
        try {
            // 1.指定加密算法
            MessageDigest digest = MessageDigest.getInstance("MD5");
            // 2.将需要加密的字符串转化成byte类型的数据，然后进行哈希过程
            byte[] bs = digest.digest(str.getBytes());
            String _md5 = appendStr(bs);
            return _md5;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加盐的MD5加密
     *
     * @param str
     * @return
     */
    public static String md5Encode(String str, String salt) {
        try {
            // 1.指定加密算法
            MessageDigest digest = MessageDigest.getInstance("MD5");
            // 2.将需要加密的字符串转化成byte类型的数据，然后进行哈希过程
            byte[] bs = digest.digest((str + salt).getBytes());
            String _md5 = appendStr(bs);
            return _md5;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拼接字符串
     *
     * @param bs
     * @return
     */
    public static String appendStr(byte[] bs) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bs) {
            int i = b & 0xff;
            String hexString = Integer.toHexString(i);
            if (hexString.length() < 2) {
                hexString = "0" + hexString;
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }


//    public static void main(String[] args) {
//        System.out.printf(Md5Utils.md5Encode("666666"));
//    }

}
