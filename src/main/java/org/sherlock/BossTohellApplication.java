package org.sherlock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.sherlock.mapper")
@SpringBootApplication
public class BossTohellApplication {
    // 主方法，Spring Boot应用的启动点
    public static void main(String[] args) {
        // 初始化并启动Spring Boot应用
        SpringApplication.run(BossTohellApplication.class, args);
    }

}