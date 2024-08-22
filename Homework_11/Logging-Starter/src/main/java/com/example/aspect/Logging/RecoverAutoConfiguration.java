package com.example.aspect.Logging;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableConfigurationProperties(RecoverProperties.class)
//@ConditionalOnProperty(value = "application.recover.enabled", havingValue = "true", matchIfMissing = true)
//public class RecoverAutoConfiguration {
//
//    @Bean
//    public RecoverAspect recoverAspect(RecoverProperties recoverProperties) {
//        return new RecoverAspect(recoverProperties);
//    }
//}

@Configuration
@ConditionalOnProperty(value = "application.recover.enabled", havingValue = "true")
@EnableConfigurationProperties(RecoverProperties.class)
public class RecoverAutoConfiguration {

    private final RecoverProperties recoverProperties;

    public RecoverAutoConfiguration(RecoverProperties recoverProperties) {
        this.recoverProperties = recoverProperties;
    }

    @Bean
    public RecoverAspect recoverAspect() {
        return new RecoverAspect(recoverProperties);
    }
}