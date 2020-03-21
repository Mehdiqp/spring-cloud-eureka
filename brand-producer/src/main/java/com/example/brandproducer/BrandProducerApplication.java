package com.example.brandproducer;

import com.example.brandproducer.controller.MController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class BrandProducerApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(
                BrandProducerApplication.class, args);

    }

}

