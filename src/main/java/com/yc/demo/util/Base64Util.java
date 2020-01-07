package com.yc.demo.util;

import java.util.Base64;


public class Base64Util {

    /**
     * 基础加密
     * @param str
     * @return
     */
    public static String encode(String str){
        byte[] encode = Base64.getEncoder().encode(str.getBytes());
        return new String(encode);
        //return new String(Base64.getUrlEncoder().encode(str.getBytes()));
    }

    /**
     * 基础解密
     * @param str
     * @return
     */
    public static String decode(String str){
        byte[] decode = Base64.getDecoder().decode(str.getBytes());
        return new String(decode);
        //return new String(Base64.getUrlDecoder().decode(str.getBytes()));
    }

    public static void main(String[] args) {
        String str = encode("hty is a pig");
        System.out.println(str);
        System.out.println(decode(str));
    }
}
