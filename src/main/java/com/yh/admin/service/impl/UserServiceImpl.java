package com.yh.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.admin.bean.condtion.UserCondtion;
import com.yh.admin.bean.entity.User;
import com.yh.admin.dao.UserDao;
import com.yh.admin.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Log4j2
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
    public PageInfo findUserPageInfo(PageInfo pageInfo, UserCondtion userCondtion) {
        log.trace("Trace log");
        log.debug("Debugging log");
        log.info("Info log");
        log.warn("Hey, This is a warning!");
        log.error("Oops! We have an Error. OK");
        log.fatal("Damn! Fatal error. Please fix me.");
        return PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize()).doSelectPageInfo(() -> userDao.findAllByEmail(userCondtion));
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
