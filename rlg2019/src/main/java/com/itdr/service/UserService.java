package com.itdr.service;


import com.itdr.common.ServiceResponse;
import com.itdr.pojo.Users;

public interface UserService {
    //用户登录
    ServiceResponse login(String username, String password);
    //检查用户名或邮箱是否存在
    ServiceResponse check_valid(String srt, String type);

    ServiceResponse register(Users user);
    //获取登录用户信息
    ServiceResponse getUserInfo(Users user);
    //获取当前登录用户的详细信息
    ServiceResponse getInforamtion(Users user);
    //登录状态更新个人信息
    ServiceResponse updateInformation(Users users);
    //登录中状态重置密码
    ServiceResponse resetPassword(String passwordOld, String passwordNew);
}
