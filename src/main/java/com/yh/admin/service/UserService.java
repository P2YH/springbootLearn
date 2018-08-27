package com.yh.admin.service;

import com.yh.admin.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findByName(String name);

    List<User> findAll();

    int insertByUser(User user);

    int updateByUser(User user);

    void delete(Long id);
}
