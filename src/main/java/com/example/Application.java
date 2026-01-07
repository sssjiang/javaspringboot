package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        // System.out.println("SpringBoot启动成功");
        // 写一个死循环，每隔1秒打印一次，这样main线程不会结束
    }
}
