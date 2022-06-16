package com.admin;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.mbg.exam.mapper")
/*@ComponentScan(basePackages = {"com.admin.*","com.mbg.exam.mapper"})
@MapperScan(value = "com.mbg.exam.mapper")*/

@MapperScan(value = "com.mbg.exam.mapper")
public class ExamAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExamAdminApplication.class, args);
    }

}
