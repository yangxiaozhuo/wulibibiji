package com.yxz.wulibibiji;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
@MapperScan("com.yxz.wulibibiji.mapper")
public class WulibibijiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WulibibijiApplication.class, args);
    }

}
