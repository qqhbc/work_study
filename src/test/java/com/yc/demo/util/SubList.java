package com.yc.demo.util;

import com.yc.demo.model.LinkPermssion;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: yinchao
 * @date 2019/7/2
 */
class Person{
    private Integer id;
    private String name;
    private String nameBy;
    private List<Person> list;


    public Person(){}

    public Person(Integer id, String name, String nameBy) {
        this.id = id;
        this.name = name;
        this.nameBy = nameBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameBy() {
        return nameBy;
    }

    public void setNameBy(String nameBy) {
        this.nameBy = nameBy;
    }

    public List<Person> getList() {
        return list;
    }

    public void setList(List<Person> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameBy='" + nameBy + '\'' +
                '}';
    }
}
public class SubList {
    private List<Person> list = new ArrayList<>();
    @Test
    public void fun(){
        List<String> list = Arrays.asList("a b v d e r h".split(" "));
        List<String> subList = Arrays.asList("a b w".split(" "));
        List<String> subList1 = Arrays.asList("a b".split(" "));
        System.out.println(list.containsAll(subList));
        System.out.println(list.containsAll(subList1));
    }
    @Test
    public void map(){
        List<LinkPermssion> list = Arrays.asList(
                new LinkPermssion(1L,1L,2L),
                new LinkPermssion(2L,1L,3L),
                new LinkPermssion(3L,1L,4L),
                new LinkPermssion(4L,1L,1L),
                new LinkPermssion(5L,2L,1L),
                new LinkPermssion(6L,2L,4L),
                new LinkPermssion(7L,3L,2L),
                new LinkPermssion(8L,3L,5L),
                new LinkPermssion(9L,3L,6L),
                new LinkPermssion(10L,4L,8L));
        Map<Long, List<Long>> map = list.stream().collect(Collectors.groupingBy(LinkPermssion::getRoleId, Collectors.mapping(LinkPermssion::getPermssionId, Collectors.toList())));
        List<Long> collect = Arrays.asList(1L,2L,3L,4L,8L);
        List<Long> roleIdList = map.entrySet().stream().filter(b -> {
            for (Long roleId : b.getValue()) {
                if (!collect.contains(roleId)) {
                    return false;
                }
            }
            return true;
        }).map(Map.Entry::getKey).collect(Collectors.toList());
        roleIdList.forEach(System.out::println);
    }

    @Test
    public void fun1(){
        new SubList().gun();
    }

    public void gun(){
        List<Person> list = Arrays.asList(
                new Person(1,"hty","yc"),
                new Person(2,"ssl","hty"),
                new Person(3,"fsw","hty"),
                new Person(4,"wpx","ssl"),
                new Person(5,"lwj","wpx"),
                new Person(5,"ll","lwj"),
                new Person(6,"dxx","yc")
        );
        List<Person> allList = createTree(list, "hty");
        System.out.println(allList);
    }

    public List<Person> createTree(List<Person> list,String name){
        List<Person> allList = new ArrayList<>();
        for(Person p : list){
            if(p.getNameBy().equals(name)){
                allList.add(p);
                p.setList(createTree(list,p.getName()));
            }
        }
        return allList;
    }


}
