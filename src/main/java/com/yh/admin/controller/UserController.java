package com.yh.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yh.admin.bean.condtion.UserCondtion;
import com.yh.admin.bean.entity.User;
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

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public ResultBean<Page<User>> userPageInfo(int pageNum, int pageSize){
        return new ResultBean<>(userService.findUsersPage(pageNum, pageSize));
    }

    /**
     * 返回带有分页信息的结果
     * @param pageInfo
     * @param email
     * @return
     */
    @GetMapping("/page/query")
    public ResultBean<PageInfo<User>> userPageQuery(PageInfo pageInfo, UserCondtion userCondtion){
        return new ResultBean<>(userService.findUserPageInfo(pageInfo, userCondtion));
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
