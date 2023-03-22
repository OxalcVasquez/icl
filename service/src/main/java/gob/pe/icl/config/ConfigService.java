/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Usuario
 */
@Configuration
@ComponentScan(basePackages = { "gob.pe.icl.service"})
public class ConfigService { 
//   
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
}
