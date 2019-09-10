package com.itdr.service.impl;

import com.itdr.mappers.UsersMapper;
import com.itdr.pojo.Users;
import com.itdr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UsersMapper usersMapper;

    @Override
    public Users login(String username, String password) {
        if (username==null&&username.equals("")&&password==null&&password.equals("")){
            return null;
        }
        Users user =usersMapper.selectByUsernameAndPassword(username,password);
        return user;
    }
}
