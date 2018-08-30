package com.yh.admin.dao;

import com.yh.admin.bean.condtion.UserCondtion;
import com.yh.admin.bean.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class UserDaoProvider {

    public String updateByUser(@Param("U") User user){
        SQL sql = new SQL(){{
            UPDATE("user");

            if (!StringUtils.isEmpty(user.getName())){
                SET("name = #{U.name}");
            }
            if (!StringUtils.isEmpty(user.getAddress())){
                SET("address = #{U.address}");
            }
            if (user.getAge() != null){
                SET("age = #{U.age}");
            }
            if (user.getSex() != null){
                SET("sex = #{U.sex}");
            }
            if (!StringUtils.isEmpty(user.getEmail())){
                SET("email = #{U.email}");
            }
            if (!StringUtils.isEmpty(user.getPhone())){
                SET("phone = #{U.phone}");
            }
            WHERE("id = #{U.id}");
        }};
        return sql.toString();
    }


    public String selectByUserCondtion(@Param("po") UserCondtion userCondtion){
        SQL sql = new SQL(){{
            SELECT("*");
            FROM("user");

            if (!StringUtils.isEmpty(userCondtion.getName())){
                WHERE("name = #{po.name}");
            }
            if (!StringUtils.isEmpty(userCondtion.getAddress())){
                WHERE("address = #{po.address}");
            }
            if (!StringUtils.isEmpty(userCondtion.getAge())){
                WHERE("age = #{po.age}");
            }
            if (!StringUtils.isEmpty(userCondtion.getSex())){
                WHERE("sex = #{po.sex}");
            }
            if (!StringUtils.isEmpty(userCondtion.getEmail())){
                WHERE("email = #{po.email}");
            }
            if (!StringUtils.isEmpty(userCondtion.getPhone())){
                WHERE("phone = #{po.phone}");
            }
            if (!StringUtils.isEmpty(userCondtion.getId())){
                WHERE("id = #{po.id}");
            }
        }};
        return sql.toString();
    }

}
