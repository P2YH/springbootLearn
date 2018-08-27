package com.yh.admin.controller;

import com.yh.admin.bean.User;
import com.yh.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/base")
    public User baseUserInfo(String name){
        return userService.findByName(name);
    }

    @GetMapping(value = "/all")
    public List<User> allUserInfo(){
        return userService.findAll();
    }

    //Json
    @PostMapping("/add")
    public int insertUser(@RequestBody User user){
        return userService.insertByUser(user);
    }

    @DeleteMapping(value = "/id")
    public void deleteUserByID(Long id){
        userService.delete(id);
    }

    @PostMapping("edit")
    public int updateUser(@RequestBody User user){
        return userService.updateByUser(user);
    }
}
