package com.itdr.service.impl;

import com.itdr.common.ServiceResponse;
import com.itdr.mappers.UsersMapper;
import com.itdr.pojo.Users;
import com.itdr.service.UserService;
import com.itdr.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersMapper usersMapper;

    //用户登录
    @Override
    public ServiceResponse login(String username, String password) {
        if (username == null || username.equals("")) {
            return ServiceResponse.defeatedSR(101, "用户名不能为空");
        }
        if (password == null || password.equals("")) {
            return ServiceResponse.defeatedSR(102, "密码不能为空");
        }
        String md5Password = MD5Utils.getMD5Code(password);
        Users user = usersMapper.selectByUsernameAndPassword(username, md5Password);
        if (user == null) {
            if (usersMapper.selectByusernameOrEmail(username, "username") < 1) {
                return ServiceResponse.defeatedSR(103, "用户名不存在");
            } else {
                return ServiceResponse.defeatedSR(103, "密码错误");
            }

        }

        return ServiceResponse.successSR(0, user);
    }

    //检查用户名或邮箱是否存在
    @Override
    public ServiceResponse check_valid(String srt, String type) {
        if (srt == null || srt.equals("") || type == null || type.equals("")) {
            return ServiceResponse.defeatedSR(100, "参数不能为空");
        }
        int row = usersMapper.selectByusernameOrEmail(srt, type);
        if (row > 0 && type.equals("username")) {
            return ServiceResponse.defeatedSR(101, "用户名已存在");
        }
        if (row > 0 && type.equals("email")) {
            return ServiceResponse.defeatedSR(101, "邮箱已注册");
        }
        return ServiceResponse.successSR(0, null, "校验成功");
    }

    //用户注册
    @Override
    public ServiceResponse register(Users user) {

        if (user.getUsername() == null || user.getUsername().equals("")) {
            return ServiceResponse.defeatedSR(100, "用户名不能为空");
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            return ServiceResponse.defeatedSR(100, "密码不能为空");
        }
        if (user.getEmail() == null || user.getEmail().equals("")) {
            return ServiceResponse.defeatedSR(100, "邮箱不能为空");
        }
        int row = usersMapper.selectByusernameOrEmail(user.getUsername(), "username");
        if (row > 0) {
            return ServiceResponse.defeatedSR(101, "用户名已注册");
        }
        int row1 = usersMapper.selectByusernameOrEmail(user.getEmail(), "email");
        if (row1 > 0) {
            return ServiceResponse.defeatedSR(101, "邮箱已注册");
        }
        user.setPassword(MD5Utils.getMD5Code(user.getPassword()));
        int row2 = usersMapper.insert(user);
        if (row2 < 1) {
            return ServiceResponse.defeatedSR(101, "注册失败");
        }
        return ServiceResponse.successSR(0, null, "注册成功");

    }

    //获取登录用户信息
    @Override
    public ServiceResponse getUserInfo(Users user) {
        if (user == null) {
            return ServiceResponse.defeatedSR(101, "用户未登录,无法获取当前用户信息");
        }

        return ServiceResponse.successSR(0, user);
    }

    //获取当前登录用户的详细信息
    @Override
    public ServiceResponse getInforamtion(Users user) {
        if (user == null) {
            return ServiceResponse.defeatedSR(101, "用户未登录,无法获取当前用户信息");
        }
        Users users = usersMapper.selectByPrimaryKey(user.getId());
        if (users == null) {
            return ServiceResponse.defeatedSR(103, "未知错误");
        }
        users.setPassword(null);
        return ServiceResponse.successSR(0, users);
    }

    //登录状态更新个人信息
    @Override
    public ServiceResponse updateInformation(Users users) {
        if (users.getEmail() != null) {
            int row1 =usersMapper.selectByusernameOrEmail(users.getEmail(),"email");
            if (row1>0){
                return ServiceResponse.defeatedSR(103,"邮箱已被注册");
            }
        }

        int row = usersMapper.updateByPrimaryKeySelective(users);
        if (row < 1) {
            return ServiceResponse.defeatedSR(103, "更新失败");
        }

        return ServiceResponse.successSR(0, null, "更新个人信息成功");
    }

    //登录中状态重置密码
    @Override
    public ServiceResponse resetPassword(String passwordOld, String passwordNew) {
        return null;
    }


}
