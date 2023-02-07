package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.sazkuyo.ultrawallpaper2.Mapper")
public class Ultrawallpaper2Application {

    public static void main(String[] args) {
        SpringApplication.run(Ultrawallpaper2Application.class, args);
    }

}
