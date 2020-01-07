package com.yc.demo.practice;

/**
 * @author: yinchao
 * @date 2019/7/1
 */
public interface Strategy {
    /**
     * 加密
     * @param str
     * @return
     */
    String encode(String str);

    /**
     * 解密
     * @param str
     * @return
     */
    String decode(String str);
}
