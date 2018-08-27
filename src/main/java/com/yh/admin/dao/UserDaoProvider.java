package com.yh.admin.dao;

import com.yh.admin.bean.User;
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


}
