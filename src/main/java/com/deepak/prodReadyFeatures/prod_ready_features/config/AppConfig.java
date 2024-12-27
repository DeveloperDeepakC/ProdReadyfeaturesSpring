package com.deepak.prodReadyFeatures.prod_ready_features.config;

import com.deepak.prodReadyFeatures.prod_ready_features.auth.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImpl")
public class AppConfig {

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    AuditorAware<String> getAuditorAwareImpl(){
        return new AuditorAwareImpl();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
