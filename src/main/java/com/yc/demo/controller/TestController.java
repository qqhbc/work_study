package com.yc.demo.controller;

import com.yc.demo.model.RePos;
import com.yc.demo.model.User;
import com.yc.demo.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试接口
 *
 * @author yinchao
 */
@Api(value = "测试接口")
@RestController
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation("demo测试")
    @GetMapping(value = "/demo")
    public String test(){
        return "success";
    }

    //  我用这个
    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public List<RePos> getList(){
        return testService.getList();
    }

    @ApiOperation("获取个人用户信息")
    @GetMapping("/detail/{id}")
    public User getDetail(@PathVariable("id") Long id){ return testService.getDetail(id); }

    @ApiOperation("修改个人信息")
    @ApiImplicitParam("用户的个人信息")
    @PutMapping("/edit")
    public String edit(@RequestBody User user){
        Integer count = testService.edit(user);
        System.out.println(count);
        return "edit success!";
    }

    @ApiOperation("添加一个用户")
    @ApiImplicitParam("add user")
    @PostMapping("/add")
    public Integer add(@RequestBody User user){
        return testService.add(user);
    }

    @ApiOperation("删除一个用户")
    @ApiImplicitParam("id")
    @DeleteMapping("/delete/{id}")
    public Integer delete(@PathVariable("id") Long id){
        return testService.delete(id);
    }

    @ApiOperation("测试短信回调")
    @PostMapping("/smd/test")
    public String test(@RequestBody String json) throws Exception {
        System.out.println("hello world!");

        log.info("==========" + json + "===========");



        return "success";
    }


}
