package com.yh.admin.dao;

import com.yh.admin.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("SELECT * FROM user WHERE name=#{name}")
    User findByName(@Param("name") String name);

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Insert("INSERT INTO USER(NAME,SEX,AGE,EMAIL,ADDRESS,PHONE) VALUES(#{name}, #{sex}, #{age}, #{email}, #{address}, #{phone})")
    int insertByUser(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);

    @UpdateProvider(type = UserDaoProvider.class, method = "updateByUser")
    int updateByUser(@Param("U") User user);
}
