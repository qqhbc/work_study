package com.yc.demo.mapper.provider;

import com.yc.demo.model.User;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author: yinchao
 * @date 2019/7/2
 */
public class TestProvider {
    public String edit(User user){
        return new SQL(){
            {
                UPDATE("t_user");
                if(user.getUserName() != null && !"".equals(user.getUserName())){
                    SET("user_name = #{user.userName}");
                }
                if(user.getAge() != 0){
                    SET("age = #{user.age}");
                }
                SET("create_time = #{user.createTime}");
                WHERE("user_name = #{user.id}");
            }

        }.toString();
    }

    public String add(User user){
        return new SQL(){
            {
                INSERT_INTO("t_user");
                if(user.getUserName() != null && "".equals(user.getUserName())){
                    VALUES("user_name","#{user.userName}");
                }
                if(user.getAge() != 0){
                    VALUES("age","#{user.age}");
                }
            }
        }.toString();
    }

    public String delete(Long id){
        return new SQL(){
            {
                DELETE_FROM("t_user");
                WHERE("id = #{id}");
            }
        }.toString();
    }

    public String getDetail(Long id){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_user");
                WHERE("id = #{id}");
            }
        }.toString();
    }
}
