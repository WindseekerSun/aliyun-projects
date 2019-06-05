package com.windseeker.aliyunuser;

import com.windseeker.aliyunuser.beans.User;
import com.windseeker.aliyunuser.dao.LoginMessageRepository;
import com.windseeker.aliyunuser.dao.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginMessageRepository repository;
    @Test
    public void addUser(){
        User user=new User();
        user.setBirthday(LocalDate.of(1987,07,07));
        user.setUserName("windseeker");
        user.setGmtCreate(LocalDateTime.now());
        user.setGmtModified(LocalDateTime.now());
        user.setNickName("Windseeker");
        user.setPhone("15169067352");
        user.setPassword("060615qf");
        user.setRealName("孙增文");
        user.setEmail("wbhgl@qq.com");
        userRepository.save(user);
        Assert.notNull(userRepository.findAll().iterator().next(),"空");
    }
}
