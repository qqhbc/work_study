package com.yc.demo.practice;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author: yinchao
 * @date 2019/7/5
 */
public class HMACSHA1Demo {
    public static String hamcSha1(String value,String key) throws Exception{
            //生成一个hamcSha1 key
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] bytes = mac.doFinal(value.getBytes());
            byte[] encode = new Hex().encode(bytes);
            return new String(encode,"UTF-8");
    }

    public static void main(String[] args) throws Exception {
        String s = hamcSha1("hty is a big", Base64.encodeBase64String("hty is a pig".getBytes()));
        System.out.println(s);
    }
}
