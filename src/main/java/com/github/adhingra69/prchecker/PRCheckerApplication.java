package com.github.adhingra69.prchecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by adhingra on 6/22/16.
 */
@SpringBootApplication
@EnableScheduling
public class PRCheckerApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PRCheckerApplication.class);
    }
}
