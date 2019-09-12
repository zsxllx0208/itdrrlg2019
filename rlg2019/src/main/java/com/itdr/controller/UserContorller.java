package com.itdr.controller;

import com.itdr.common.ServiceResponse;
import com.itdr.pojo.Users;
import com.itdr.service.UserService;
import com.itdr.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserContorller {

    @Autowired
    private UserService userService;

    //用户登录
    @RequestMapping("/login.do")
    public ServiceResponse<Users> login(String username, String password, HttpSession session) {
        ServiceResponse sr = userService.login(username, password);
        if (sr.getStatus().equals(0)) {
            Users users = (Users) sr.getData();
            users.setPassword(null);
            session.setAttribute("user", users);
            Users users1 = users;
            users1.setQuestion(null);
            users1.setAnswer(null);
            users1.setRole(null);
            sr.setData(users1);
        }

        return sr;
    }

    //检查用户名或邮箱是否存在
    @RequestMapping("/check_valid.do")
    public ServiceResponse<Users> check_valid(String srt, String type) {
        ServiceResponse sr = userService.check_valid(srt, type);

        return sr;
    }

    //用户注册
    @RequestMapping("/register.do")
    public ServiceResponse<Users> register(Users user) {
        ServiceResponse sr = userService.register(user);

        return sr;
    }

    //获取登录用户信息
    @RequestMapping("/get_user_info.do")
    public ServiceResponse<Users> getUserInfo(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        ServiceResponse sr = userService.getUserInfo(user);

        return sr;
    }

    //退出登录
    @RequestMapping("/logout.do")
    public ServiceResponse<Users> logout(HttpSession session) {
        session.removeAttribute("user");

        return ServiceResponse.successSR(0, null, "退出成功");
    }

    //获取当前登录用户的详细信息
    @RequestMapping("/get_inforamtion.do")
    public ServiceResponse<Users> getInforamtion(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        ServiceResponse sr = userService.getInforamtion(user);

        return sr;
    }

    //登录状态更新个人信息
    @RequestMapping("/update_information.do")
    public ServiceResponse<Users> updateInformation(Users users, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return ServiceResponse.defeatedSR(101, "用户未登录");
        } else {
            users.setId(user.getId());
            ServiceResponse sr = userService.updateInformation(users);
            return sr;
        }

    }

    //登录中状态重置密码
    @RequestMapping("/reset_password.do")
    public ServiceResponse<Users> resetPassword(String passwordOld, String passwordNew,HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user==null){
            return ServiceResponse.defeatedSR(101, "用户未登录");
        } else {
            ServiceResponse sr = userService.resetPassword(passwordOld,passwordNew);

            return sr;
        }

    }
}
