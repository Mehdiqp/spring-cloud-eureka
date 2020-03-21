package com.example.brandproducer.controller;


import com.example.brandproducer.model.BrandDTO;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;

@Controller
public class MController {


    //with this interface we could search in nodes
    private final DiscoveryClient discoveryClient;

    public MController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    //in this controller we want to create in brand service
    //with spring cloud [eureka services]
    //post some object for create brand in brand service node
    @PostMapping("/ali")
    public ResponseEntity<?> postBrand(@RequestBody BrandDTO brandDTO) throws URISyntaxException {
        //rest template for send request
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = null;
        URI uri = new URI(getUrlFromInstanceName("brand-service"));
        try {
            response = restTemplate.postForEntity(uri, brandDTO, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("ok shod", HttpStatus.OK);
    }

    //in this controller  we want to fetch all brand from brand service
    //with spring cloud
    @GetMapping(value = "all")
    public ResponseEntity<?> getBrand() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList> response = null;
            //fk nakonam chize khasi dashte bashe vase tozih
        try {
            response = restTemplate.exchange(getUrlFromInstanceName("brand-service")
                    , HttpMethod.GET, getHeader(), ArrayList.class);
        } catch (Exception e) {
            System.out.println(e);
        }

        return response != null ? new ResponseEntity<>(response.getBody(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //ba in method header doros mikonam ke inja to accept json set mikonam
    //age brand service ro dide bashid to controller haye ke data response mikonan
    //gofte shode ke format data ro bedim be method
    //inja ham ma json gozashtim
    //age XML khasti khate zzz ro xml KON
    private static HttpEntity<?> getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);//zzz
        return new HttpEntity<>(headers);
    }


    //in method ham name on service ke alan to eureka load hast ro midim
    //url on instanse ro mide
    public String getUrlFromInstanceName(String s) {
        List<ServiceInstance> instances = discoveryClient.getInstances(s);
        ServiceInstance instance = instances.get(0);
        String baseUrl = instance.getUri().toString();
        return baseUrl + "/v1/brand";
    }
}
