package com.flora.chatter;

import com.flora.chatter.Repository.AppUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = AppUserRepository.class)
public class ChatterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatterApplication.class, args);
    }

}
