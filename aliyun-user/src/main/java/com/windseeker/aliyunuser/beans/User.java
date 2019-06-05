package com.windseeker.aliyunuser.beans;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Windseeker
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String userName;
    private String password;
    private String nickName;
    private String realName;
    private LocalDate birthday;
    private String phone;
    private String email;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
