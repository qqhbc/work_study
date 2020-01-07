package com.yc.demo.java8;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author: yinchao
 * @date 2019/7/19
 */
@Slf4j
public class LambdaDemo {
    @Test
    public void sort(){
        List<String> names = Arrays.asList("hty", "ssl", "wpx", "fsw");
        names.sort(Comparator.reverseOrder());
        log.info(JSON.toJSONString(names));
    }
}
