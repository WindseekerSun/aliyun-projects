package com.windseeker.aliyunuser.service;

import com.windseeker.aliyunuser.beans.User;
import com.windseeker.aliyunuser.utils.RegisteEnum;

/**
 * @author Windseeker
 * 用户服务的service
 */
public interface UserService {
    /**
     * 登陆操作，返回token
     *
     * @param user
     * @return
     */
    String login(User user,String ip);

    /**
     * 返回结果（注册成功、用户名已经存在、手机号码已经存在、邮箱已经存在、未知错误）
     *
     * @param user
     * @return
     */
    RegisteEnum registe(User user);

    /**
     * 用户登出
     * @param user
     */
    void logout(User user);
}
