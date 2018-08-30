package com.yh.admin.bean.entity;

import lombok.Data;

@Data
public class User{
    private Long id;
    private String name;
    private Integer sex;
    private Integer age;
    private String email;
    private String address;
    private String phone;
}
