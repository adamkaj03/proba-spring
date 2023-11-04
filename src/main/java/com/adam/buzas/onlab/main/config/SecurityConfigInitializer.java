package com.adam.buzas.onlab.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class SecurityConfigInitializer extends GlobalAuthenticationConfigurerAdapter {
/*
    @Autowired
    private FelhasznaloService felhasznaloService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(felhasznaloService).passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/
}

