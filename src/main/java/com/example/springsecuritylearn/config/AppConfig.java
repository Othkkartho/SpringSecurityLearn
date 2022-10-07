package com.example.springsecuritylearn.config;

import com.example.springsecuritylearn.repository.ResourcesRepository;
import com.example.springsecuritylearn.service.SecurityResourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppConfig {
    @Bean
    public SecurityResourceService securityResourceService(ResourcesRepository resourcesRepository) {
        return new SecurityResourceService(resourcesRepository);
    }
}
