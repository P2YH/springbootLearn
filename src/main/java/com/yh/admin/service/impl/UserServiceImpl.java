package com.yh.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.admin.bean.User;
import com.yh.admin.dao.UserDao;
import com.yh.admin.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Page<User> findUsersPage(int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> userDao.findAll());
    }

    @Override
    public PageInfo findUserPageInfo(PageInfo pageInfo, String email) {
        return PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize()).doSelectPageInfo(() -> userDao.findAllByEmail(email));
    }

    @Override
    public int insertByUser(User user) {
        return userDao.insertByUser(user);
    }

    @Override
    public int updateByUser(User user) {
        return userDao.updateByUser(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}
