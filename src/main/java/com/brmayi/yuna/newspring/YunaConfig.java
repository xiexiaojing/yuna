package com.brmayi.yuna.newspring;

import com.brmayi.yuna.config.JettyConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.brmayi.yuna.newspring")
@Import(JettyConfig.class)
public class YunaConfig {
}