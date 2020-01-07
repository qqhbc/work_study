package com.yc.demo.constant;

/**
 * @author: yinchao
 * @date 2019/7/2
 */
public enum RedisKey {
    /**
     * 用户列表key
     */
    USER_LIST("user_list"),

    /**
     * 单个用户key
     */
    USER_ID("user_");
    private String msg;
    RedisKey(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
