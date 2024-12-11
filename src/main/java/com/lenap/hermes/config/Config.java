package com.lenap.hermes.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;

@Configuration
public class Config {

//    private final Environment env;

//    public Config(Environment environment) {
//        this.env = environment;
//    }

//    @Bean //TODO
//    public String getSecret() {
//        return env.getProperty("JWT_KEY");
//    }

    @Bean
    FilterRegistrationBean<AuthenticationFilter> authenticationFilterFilterRegistrationBean( AuthenticationFilter authenticationFilter) {
        FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean = new FilterRegistrationBean<>(authenticationFilter);
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistrationBean;
    }
}
