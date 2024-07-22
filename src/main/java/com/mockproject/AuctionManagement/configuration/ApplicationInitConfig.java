package com.mockproject.AuctionManagement.configuration;

import com.mockproject.AuctionManagement.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationInitConfig {
    ApplicationRunner applicationRunner(UserRepository userRepository) {
         return args -> {

         };
    }
}