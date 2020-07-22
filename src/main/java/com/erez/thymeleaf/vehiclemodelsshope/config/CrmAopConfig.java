package com.erez.thymeleaf.vehiclemodelsshope.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.erez.thymeleaf.vehiclemodelsshope.*")
public class CrmAopConfig  {

}
