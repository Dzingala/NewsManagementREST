package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by User on 10/29/2016.
 */
@SpringBootApplication
@ImportResource("classpath*:SpringModule.xml")
@EnableAutoConfiguration
@EntityScan(basePackages = "by.epam.lab.task.entity")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
