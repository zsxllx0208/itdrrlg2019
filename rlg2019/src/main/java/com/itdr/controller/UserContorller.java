package com.itdr.controller;

import com.itdr.pojo.Users;
import com.itdr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manage/user/")
public class UserContorller {
    @Autowired
    private UserService userService;

    @RequestMapping("login.do")
    public ModelAndView login(String username, String password, ModelAndView model) {
        Users users = userService.login(username, password);
        model.addObject("u",users);
        model.setViewName("home");
        return model;
    }
}
