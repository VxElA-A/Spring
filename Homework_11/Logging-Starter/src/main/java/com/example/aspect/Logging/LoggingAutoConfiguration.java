package com.example.aspect.Logging;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnProperty(value = "application.logging.enabled", havingValue = "true")
//@ConditionalOnClass(Aspect.class)
public class LoggingAutoConfiguration {


    @Bean
    @ConditionalOnProperty(value = "application.logging.print-args", havingValue = "true", matchIfMissing = true)
    public LoggingAspect loggingAspect(LoggingProperties loggingProperties){

        return new LoggingAspect(loggingProperties);

    }

}
