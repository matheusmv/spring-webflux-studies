package com.github.matheusmv.springwebfluxstudies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class SpringWebfluxStudiesApplication {

    static {
        BlockHound.install();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxStudiesApplication.class, args);
    }

}
