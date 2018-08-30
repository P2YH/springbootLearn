package com.yh.admin.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yh.admin.bean.condtion.UserCondtion;
import com.yh.admin.bean.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findByName(String name);

    List<User> findAll();

    Page<User> findUsersPage(int pageNum, int pageSize);

    PageInfo findUserPageInfo(PageInfo pageInfo, UserCondtion userCondtion);

    int insertByUser(User user);

    int updateByUser(User user);

    void delete(Long id);
}
