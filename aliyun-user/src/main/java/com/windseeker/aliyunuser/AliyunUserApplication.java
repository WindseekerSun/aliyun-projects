package com.windseeker.aliyunuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Windseeker
 */
@EnableEurekaClient
@SpringBootApplication
public class AliyunUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliyunUserApplication.class, args);
    }

}
