package com.yc.demo.practice;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要算法-MD5不可逆
 *
 * @author: yinchao
 * @date 2019/7/1
 */
public class MD5Strategy implements Strategy {
    @Override
    public String encode(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(str.getBytes());
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String decode(String str) {
        throw new RuntimeException("MD5 no encode");
    }
}
