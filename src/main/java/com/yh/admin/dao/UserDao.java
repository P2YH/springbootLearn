package com.yh.admin.dao;

import com.yh.admin.bean.condtion.UserCondtion;
import com.yh.admin.bean.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("SELECT * FROM user WHERE name=#{name}")
    User findByName(@Param("name") String name);

    @SelectProvider(type = UserDaoProvider.class, method = "selectByUserCondtion")
    List<User> findAllByEmail(@Param("po") UserCondtion userCondtion);

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Insert("INSERT INTO USER(NAME,SEX,AGE,EMAIL,ADDRESS,PHONE) VALUES(#{name}, #{sex}, #{age}, #{email}, #{address}, #{phone})")
    int insertByUser(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);

    @UpdateProvider(type = UserDaoProvider.class, method = "updateByUser")
    int updateByUser(@Param("U") User user);
}
