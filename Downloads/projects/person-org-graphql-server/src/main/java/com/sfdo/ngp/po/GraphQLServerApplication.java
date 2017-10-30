package com.sfdo.ngp.po;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication

//@ComponentScan(basePackages="com.sfdo.ngp")
@EntityScan("com.sfdo.ngp.data.schema")
@EnableJpaRepositories("com.sfdo.ngp.persistence")
public class GraphQLServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphQLServerApplication.class, args);
    }
}
