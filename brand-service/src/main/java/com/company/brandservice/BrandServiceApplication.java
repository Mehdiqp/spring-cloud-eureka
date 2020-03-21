package com.company.brandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


//hala ma ye service create mikonim ke mikhaym az eureka server estefade kone
//inam ke khasi nadare
//moghe initial karadan spring boot eureka client ro add mikonim
// badesh EnableDiscoveryClient be start class ezafe mikonim
//va badesh address eureka server ro to ghesmate config ezafe mikonim
//baghie chizam ke crud sade mibashad
@SpringBootApplication
@EnableDiscoveryClient
public class BrandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrandServiceApplication.class, args);
    }

}
