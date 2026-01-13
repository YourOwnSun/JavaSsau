package com.shpyakin.javassau.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.shpyakin.javassau")
@ComponentScan(basePackages = "com.shpyakin.javassau")
@EnableTransactionManagement
public class AppConfig {
}
