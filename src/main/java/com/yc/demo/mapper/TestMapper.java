package com.yc.demo.mapper;

import com.yc.demo.mapper.provider.TestProvider;
import com.yc.demo.model.RePos;
import com.yc.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface TestMapper {

    @Select("select * from t_user limit 10,10")
    @Results({
            @Result(column = "user_name",property = "userName"),
            @Result(column = "create_time",property = "createTime")
    })
    List<User> getList();

    /**  @Update("update t_user set user_name = #{user.userName},age = #{user.age} where id = #{user.id}") */
    @UpdateProvider(type = TestProvider.class,method = "edit")
    Integer edit(@Param(value = "user")User user);

    @Insert("insert into t_user (user_name,age) values(#{user.userName},#{user.age})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    Integer add(@Param(value = "user") User user);

    @Delete("delete from t_user where id = #{id}")
    Integer delete(@Param("id") Long id);

    @Select("select * from t_user where id = #{id} for update")
    @Results({
            @Result(column = "user_name",property = "userName"),
            @Result(column = "create_time",property = "createTime")
    })
    User getDetail(@Param("id") Long id);

    @Select("select * from re_pos")
    List<RePos> getAll();
}
