package com.erez.thymeleaf.vehiclemodelsshope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class VehicleModelsShopeApplication {


    public static void main(String[] args) {
        SpringApplication.run(VehicleModelsShopeApplication.class, args);


    }


}
