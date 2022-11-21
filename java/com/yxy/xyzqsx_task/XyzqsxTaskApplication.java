package com.yxy.xyzqsx_task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yxy.xyzqsx_task.mapper")
public class XyzqsxTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyzqsxTaskApplication.class, args);
    }

}
