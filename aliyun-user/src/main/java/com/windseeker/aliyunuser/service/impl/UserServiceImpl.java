package com.windseeker.aliyunuser.service.impl;

import com.windseeker.aliyunuser.beans.LoginMessage;
import com.windseeker.aliyunuser.beans.User;
import com.windseeker.aliyunuser.dao.LoginMessageRepository;
import com.windseeker.aliyunuser.dao.UserRepository;
import com.windseeker.aliyunuser.service.UserService;
import com.windseeker.aliyunuser.utils.Encrypt;
import com.windseeker.aliyunuser.utils.LoginEnum;
import com.windseeker.aliyunuser.utils.RegisteEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Windseeker
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private LoginMessageRepository loginMessageRepository;

    /**
     * 登陆操作。
     * 1.如果登陆未成功，返回登陆出错原因
     * 2.如果登陆成功，返回用户的token
     *
     * @param user
     * @return
     */
    @Override
    public String login(User user, String ip) {
        String phone = user.getPhone();
        String userName = user.getUserName();
        String email = user.getEmail();
        String password = user.getPassword();
        boolean blankFlag = StringUtils.isEmpty(password) || ((StringUtils.isEmpty(phone) &&
                StringUtils.isEmpty(userName) &&
                StringUtils.isEmpty(email)));
        if (blankFlag) {
            return LoginEnum.BLANK_PASSWORD_OR_USER.name();
        }
        User authUser = null;
        String encryptedPassword = Encrypt.encryptPassword(password);
        if (!StringUtils.isEmpty(userName)) {
            authUser = userRepository.findByUserName(userName);
        } else if (authUser == null && !StringUtils.isEmpty(phone)) {
            authUser = userRepository.findByPhone(userName);
        } else if (authUser == null && !StringUtils.isEmpty(email)) {
            authUser = userRepository.findByEmail(email);
        }
        if (authUser == null || !encryptedPassword.equals(authUser.getPassword())) {
            return LoginEnum.WRONG_USER_OR_PASSWORD.name();
        } else {
            saveLoginMessage(authUser, ip);
            return getToken(authUser, ip);
        }
    }


    @Override
    public RegisteEnum registe(User user) {
        String userName = user.getUserName();
        String phone = user.getPhone();
        String email = user.getEmail();
        if(StringUtils.isEmpty(userName)&&StringUtils.isEmpty(phone)
                &&StringUtils.isEmpty(email)){
            return RegisteEnum.UNKOWN_ERROR;
        }
        String password = user.getPassword();
        User registedUser = null;
        if (userName != null) {
            registedUser = userRepository.findByUserName(userName);
        }
        if (registedUser != null) {
            return RegisteEnum.USERNAME_USED;
        }
        if (phone != null) {
            registedUser = userRepository.findByPhone(phone);
        }
        if (registedUser != null) {
            return RegisteEnum.PHONE_USED;
        }
        if (email != null) {
            registedUser = userRepository.findByEmail(email);
        }
        if (registedUser != null) {
            return RegisteEnum.EMAIL_USED;
        }
        String encryptedPassword = Encrypt.encryptPassword(password);
        user.setPassword(encryptedPassword);
        user.setGmtCreate(LocalDateTime.now());
        user.setGmtModified(LocalDateTime.now());
        registedUser = userRepository.save(user);
        if (registedUser.getId() != null) {
            return RegisteEnum.REGISTE_SUCCESS;
        } else {
            return RegisteEnum.UNKOWN_ERROR;
        }
    }

    @Override
    public void logout(User user) {
        redisTemplate.delete("user_login_" + user.getId());
    }

    /**
     * 保存用户本次登陆的信息
     *
     * @param authUser
     * @param ip
     */
    private void saveLoginMessage(User authUser, String ip) {
        LoginMessage loginMessage = new LoginMessage();
        loginMessage.setUserId(authUser.getId());
        loginMessage.setLoginTime(LocalDateTime.now());
        loginMessage.setIp(ip);
        loginMessageRepository.save(loginMessage);
    }

    /**
     * 根据用户获取token并存入缓存中
     *
     * @param authUser
     * @return
     */
    private String getToken(User authUser, String ip) {
        Long id = authUser.getId();
        String uuid = UUID.randomUUID().toString();
        String userRedisKey = "user_login_" + id;
        redisTemplate.boundHashOps(userRedisKey).put("token", uuid);
        redisTemplate.boundHashOps(userRedisKey).put("ip", ip);
        redisTemplate.expire(userRedisKey, 30, TimeUnit.MINUTES);
        return uuid;
    }
}
