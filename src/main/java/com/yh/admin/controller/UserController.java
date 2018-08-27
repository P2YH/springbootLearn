package com.yh.admin.controller;

import com.yh.admin.bean.User;
import com.yh.admin.common.beans.ResultBean;
import com.yh.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/base")
    public ResultBean<User> baseUserInfo(String name){
        return new ResultBean<>(userService.findByName(name));
    }

    @GetMapping("/all")
    public ResultBean<List<User>> allUserInfo(){
        return new ResultBean<>(userService.findAll());
    }

    //Json
    @PostMapping("/add")
    public ResultBean<Integer> insertUser(@RequestBody User user){
        return new ResultBean<>(userService.insertByUser(user));
    }

    @DeleteMapping("/delete")
    public void deleteUserByID(Long id){
        userService.delete(id);
    }

    @PostMapping("/edit")
    public ResultBean<Integer>  updateUser(@RequestBody User user){
        return new ResultBean<>(userService.updateByUser(user));
    }
}
