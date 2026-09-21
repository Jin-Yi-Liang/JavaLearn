package org.maxing.learning.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"org.maxing.learning.controller", "org.maxing.learning.service"})
@EnableWebMvc
public class SpringWebMvcConfig {
}
