package com.yc.demo.practice.stream;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author: yinchao
 * @date 2019/7/9
 */
public class StreamDemo {

    @Test
    public void fun(){
        IntStream.range(1,3).forEach(System.out::print);
        IntStream.rangeClosed(1,3).forEach(System.out::print);
        System.out.println();
        ArrayList<Integer> collect = Stream.of(3, 5, 4, 7, 8).sorted().peek(e -> System.out.println(e)).collect(Collectors.toCollection(ArrayList::new));
        collect.forEach(System.out::print);
    }

    @Test
    public void mapCompareFlatMap(){
        List<Integer> integerStream = Stream.of(Arrays.asList(1, 3), Arrays.asList(2, 3), Arrays.asList(4, 5, 6)).flatMap(b -> b.stream()).distinct().collect(Collectors.toList());
        Stream.of(Arrays.asList(1, 3), Arrays.asList(2, 3), Arrays.asList(4, 5, 6)).forEach(System.out::println);
        integerStream.forEach(System.out::print);
    }

    @Test
    public void readTxt() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\M70ABZFJ\\Desktop\\date.txt")));
        //List<String> collect = reader.lines().flatMap(line -> Stream.of(line.split(" "))).filter(length -> length.length() > 2).collect(Collectors.toList());
       // System.out.println(" words total  "+collect.size());
       // collect.forEach(b -> System.out.print(b + "、"));
        System.out.println("========================================================================");
        List<String[]> collect1 = reader.lines().map(line -> line.split(" ")).collect(Collectors.toList());
        collect1.forEach(b -> System.out.println(b +"、"));

        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"3","ad","ww"});
        list.add(new String[]{"1","ad","bb"});
        list.add(new String[]{"2","ad","cc"});

        list.forEach(b -> Arrays.stream(b).forEach(System.out::println));
        List<String> strList = new ArrayList<>();
        strList.add("this is a big");
        strList.add("hello world");
        List<String[]> collect2 = strList.stream().map(b -> b.split(" ")).collect(Collectors.toList());
        System.out.println();
    }

    @Test
    public void verify(){
        AtomicInteger integer = new AtomicInteger(0);
        System.out.println(integer.incrementAndGet());
        System.out.println(integer.decrementAndGet());
    }
}
