package com.example.DZNine;

/**
 * Домашнее задание:
 * 1. Повторить код с урока
 * 2. Запустить Eureka, Запустить 2 части (rest + page)
 * 3. В качестве результата работы прислать 1 скриншот со страницы localhost:8761,
 * где видно оба запущенных приложения (Instances currently registered with Eureka)
 * 4. **** Попробовать запустить несколько экземпляров (instances) timesheets-rest
 * 5. **** Поизучать про RestTemplate. Попробовать завести его с использованием аннотации LoadBalanced
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DZNinePageApplication {

    public static void main(String[] args) {

        SpringApplication.run(DZNinePageApplication.class, args);

    }
}