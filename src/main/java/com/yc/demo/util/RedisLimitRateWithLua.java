package com.yc.demo.util;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;

/**
 * @author: yinchao
 * @date 2019/7/11
 */
public class RedisLimitRateWithLua {
    public static void main(String[] args) throws Exception{
        accquire();
    }
    public static boolean accquire() throws Exception{
        String path = RedisLimitRateWithLua.class.getResource("/static/limit.lua").toURI().getPath();
        File file = new File(path);
        return true;
    }
}
