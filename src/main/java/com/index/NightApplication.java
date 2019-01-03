package com.index;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan({
        "com.index.management.mapper",
        "com.index.video.mapper",
        "com.index.picture.mapper",
        "com.index.security.mapper"})
@EnableTransactionManagement(proxyTargetClass = true)
public class NightApplication {

    public static void main(String[] args) {
        SpringApplication.run(NightApplication.class, args);
    }
}
