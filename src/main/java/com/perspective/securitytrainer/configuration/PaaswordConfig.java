package com.perspective.securitytrainer.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PaaswordConfig {
   @Bean
    public PasswordEncoder passwordEncoder(){

      return  new BCryptPasswordEncoder(10);
    }
}
