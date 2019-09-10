package com.itdr.service;


import com.itdr.pojo.Users;

public interface UserService {
    Users login(String username, String password);
}
