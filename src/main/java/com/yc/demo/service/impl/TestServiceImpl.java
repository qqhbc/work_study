package com.yc.demo.service.impl;

import com.google.common.util.concurrent.RateLimiter;
import com.yc.demo.constant.RedisKey;
import com.yc.demo.mapper.TestMapper;
import com.yc.demo.model.RePos;
import com.yc.demo.model.User;
import com.yc.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 测试crud
 *
 * @author yinchao
 * @date 2019/7/2
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    RateLimiter rateLimiter = RateLimiter.create(5.0);
    AtomicInteger atomicInteger = new AtomicInteger(0);
    private int count;

    @Override
    public List<RePos> getList() {
        List<RePos> list = testMapper.getAll();
        List<String> collect = list.stream().filter(b -> !b.getPos_bank_account_name().trim().equals(b.getRe_bank_account_name().trim()) || !b.getPos_bank_account_number().trim().equals(b.getRe_bank_account_number().trim())
                || !b.getPos_bank_code().trim().equals(b.getRe_bank_number().trim()) || !b.getPos_bank_name().trim().equals(b.getRe_bank_name().trim())
        ).map(RePos::getPos_id).collect(Collectors.toList());
        collect.forEach(System.out::println);

//        //从缓存中获取用户列表
//        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
//
//        //缓存是否存在
//        if(redisTemplate.hasKey(RedisKey.USER_LIST.getMsg())){
//            List<User> list = (List<User>) operations.get(RedisKey.USER_LIST.getMsg());
//            log.info("TestServiceImpl.getList()  缓存中读取用户列表信息 -> "+list.size());
//            return list;
//        }
//        List<User> list = testMapper.getList();
//        operations.set(RedisKey.USER_LIST.getMsg(),list,10, TimeUnit.MINUTES);
//        log.info("TestServiceImpl.getList() 从数据库中获取用户列表信息 -> "+list.size());

        return list;
    }

    @Override
    @Transactional
    public Integer edit(User user) {
        Random random = new Random();
        User temp = testMapper.getDetail(user.getId());
        log.info(Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        user.setCreateTime(LocalDateTime.now());
        user.setUserName(Thread.currentThread().getName());
        Integer count = testMapper.edit(user);
        log.info("时间 {}" ,System.currentTimeMillis());
        String key = RedisKey.USER_ID.getMsg()+user.getId();
//        if(count == 1){
//            if(redisTemplate.hasKey(key)){
//                redisTemplate.delete(key);
//                log.info("TestServiceImpl.edit() 将缓存中的个人信息移除 ->"+RedisKey.USER_ID.getMsg()+user.getId());
//            }
//        }
        return count;

    }

    @Override
    public Integer add(User user) {
        Integer count = testMapper.add(user);
        String key = RedisKey.USER_ID.getMsg()+user.getId();
        if(count == 1){
            redisTemplate.opsForValue().set(key,user,10,TimeUnit.MINUTES);
            log.info("TestServiceImpl.add() 个人信息添加到缓存中 ->"+user);
        }
        return count;
    }

    @Override
    public Integer delete(Long id) {
        Integer count = testMapper.delete(id);
        String key = RedisKey.USER_ID.getMsg()+id;
        if(count == 1){
            if(redisTemplate.hasKey(key)){
                redisTemplate.delete(key);
                log.info("TestServiceImpl.delete() 将缓存中的个人信息移除 ->"+RedisKey.USER_ID.getMsg()+id);
            }
        }
        return count;
    }

    @Override
    public User getDetail(Long id) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info(request.getSession().getId());


//        if(!rateLimiter.tryAcquire()){
//            User user = new User();
//            user.setUserName("限流啦！！");
//            return user;
//        }
        synchronized (this) {
        try {
            //if (atomicInteger.incrementAndGet() > 2) {


                if (++count > 2) {
                    User user = new User();
                    user.setUserName("限流啦！！");
                    return user;
                }
//            String key = RedisKey.USER_ID.getMsg() + id;
//            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
//
//            if (redisTemplate.hasKey(key)) {
//                User user = (User) operations.get(key);
//                log.info("TestServiceImpl.getDetail() 从缓存中读取个人信息 -> " + user);
//                return user;
//            }
                User user = testMapper.getDetail(id);
                System.out.println(user.getUserName());
                //  operations.set(key, user, 1, TimeUnit.MINUTES);
                log.info("TestServiceImpl.getDetail() 从数据库中读取个人信息 -> " + user);
                return user;

            }finally{
                //atomicInteger.decrementAndGet();
                --count;
            }
        }
    }
}
