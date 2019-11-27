package com.yc.demo.base;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: yinchao
 * @date 2019/7/18
 */
class Person{
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
public class Demo {
    @Test
    public void function(){
        List<Person> list = new ArrayList<>();
        Objects.requireNonNull(list);
        System.out.println("aa");
    }
}
