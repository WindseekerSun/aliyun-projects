package com.windseeker.aliyunuser.dao;

import com.windseeker.aliyunuser.beans.LoginMessage;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Windseeker
 */
public interface LoginMessageRepository extends CrudRepository<LoginMessage, Long> {
    /**
     * 根据用户id查找登陆信息
     *
     * @param userId
     * @return
     */
    Optional<LoginMessage> findByUserId(Long userId);

    /**
     * 查询某段时间内登陆用户的数量
     *
     * @param start
     * @param end
     * @return
     */
    Optional<LoginMessage> findByLoginTimeBetween(LocalDateTime start, LocalDateTime end);
}
