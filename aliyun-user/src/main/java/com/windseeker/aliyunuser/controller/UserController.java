package com.windseeker.aliyunuser.controller;

import com.windseeker.aliyunuser.beans.User;
import com.windseeker.aliyunuser.service.UserService;
import com.windseeker.aliyunuser.utils.CusAccessObjectUtil;
import com.windseeker.aliyunuser.utils.RegisteEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户相关的controller
 *
 * @author Windseeker
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/registe")
    public RegisteEnum registe(@RequestBody User user) {
        return userService.registe(user);
    }
    @RequestMapping("/login")
    public String login(HttpServletRequest request,@RequestBody User user){
        String ip= CusAccessObjectUtil.getIpAddress(request);
        return userService.login(user,ip);
    }
}
