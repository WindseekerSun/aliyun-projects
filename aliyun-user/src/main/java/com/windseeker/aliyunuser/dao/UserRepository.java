package com.windseeker.aliyunuser.dao;

import com.windseeker.aliyunuser.beans.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Windseeker
 */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * 按照userName查找用户
     *
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 根据邮箱查找用户
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 根据手机号查找用户
     * @param phone
     * @return
     */
    User findByPhone(String phone);
}
