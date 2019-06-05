package com.windseeker.aliyunuser.utils;

/**
 * 注册操作结果
 */
public enum RegisteEnum {
    /**
     * 注册成功
     */
    REGISTE_SUCCESS,
    /**
     * 用户名已经存在
     */
    USERNAME_USED,
    /**
     * 手机号已经存在
     */
    PHONE_USED,
    /**
     * 邮箱已经存在
     */
    EMAIL_USED,
    /**
     * 未知错误
     */
    UNKOWN_ERROR
}
