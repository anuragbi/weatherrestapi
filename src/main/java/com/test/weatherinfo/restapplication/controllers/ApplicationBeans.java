package com.test.weatherinfo.restapplication.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.weatherinfo.restapplication.integration.service.HttpClientService;
import com.test.weatherinfo.restapplication.service.InfoService;
import com.test.weatherinfo.restapplication.service.impl.InfoServiceImpl;


@Configuration
public class ApplicationBeans {
    @Value("${temperature.service.location}")
    private String darkSkyServiceLocation;

    
//    @Bean
//    public InfoService getTemp()
//    {
//    	return new InfoServiceImpl(new HttpClientService());
//    }
    
}

