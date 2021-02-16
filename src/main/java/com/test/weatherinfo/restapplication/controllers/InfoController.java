package com.test.weatherinfo.restapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.weatherinfo.restapplication.model.Response;
import com.test.weatherinfo.restapplication.service.InfoService;


@RestController
public class InfoController {

	@Value("${default.zipcode}")
	private String defaultZipcode;

	@Autowired
	InfoService InfoService;
	
	
	
	@GetMapping("/weatherinfo")
	public Response greeting(@RequestParam(value = "cityName", defaultValue = "London") String cityName) {
		Response response = null;
		try {
			response = InfoService.getMinTemperatureOfCity(cityName);
			System.out.println("This is min temp::::::::::::::::" + response.getMain().getTemp_min());
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

}
