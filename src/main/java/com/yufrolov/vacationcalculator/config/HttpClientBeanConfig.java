package com.yufrolov.vacationcalculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class HttpClientBeanConfig {

    @Bean
    public HttpClient getHttpClient() {
        return HttpClient.newHttpClient();
    }
}
