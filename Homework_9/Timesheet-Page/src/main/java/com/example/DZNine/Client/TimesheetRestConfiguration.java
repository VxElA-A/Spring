package com.example.DZNine.Client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

//@Configuration
//public class TimesheetRestConfiguration {
//
//    @Bean
//    @LoadBalanced
//    public RestClient timesheetRestClient() {
//        return RestClient.create("http://timesheet-rest");
//    }
//}

@Configuration
public class TimesheetRestConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}