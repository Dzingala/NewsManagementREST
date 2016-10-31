package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Ivan Dzinhala
 */
@SpringBootApplication
@ImportResource("classpath*:SpringModule.xml")
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class})
//@EntityScan(basePackages = "by.epam.lab.task.entity")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
