package com.company.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


//wase inke eureka server btonim injad konim kare shaghi niaz nist bokonim
//faghat kafie ke
//  1-dependency eureka server ro toye spring boot initializer entekhab konim
//  2-Annotation EnableEurekaServer ro be class main ezafe konim ====> class main ro hatman to pom moshakhas konid
// 3- dar akhar config haye eureka server ro injam bedid ====> application.yml ro bebinid
@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceApplication.class, args);
    }

}
