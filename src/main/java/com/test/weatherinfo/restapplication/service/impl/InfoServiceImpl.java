package com.test.weatherinfo.restapplication.service.impl;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.weatherinfo.restapplication.integration.service.HttpClientService;
import com.test.weatherinfo.restapplication.model.Response;
import com.test.weatherinfo.restapplication.model.ResponseCodeAndBody;
import com.test.weatherinfo.restapplication.service.InfoService;

@Service
public class InfoServiceImpl implements InfoService {

	@Autowired
	HttpClientService httpClientService;

	@Value("${service.url}")
	private String serviceUrl;

	@Autowired
	public InfoServiceImpl(HttpClientService httpClientService) {
		this.httpClientService = httpClientService;
	}

	@Override
	public Response getMinTemperatureOfCity(String cityName) {
		
		Response response= null;
		try {
			String urlNew=serviceUrl+cityName+"&appid=cd1d4bba4d7f99bb6237976f36783135";
	            ResponseCodeAndBody responseCodeAndBody = httpClientService.get(urlNew);
	            System.out.println("New URL::"+urlNew);
	            System.out.println(responseCodeAndBody.getBody());
	            Gson gson = new Gson();
	            Type type = new TypeToken<Response>() {
	            }.getType();
	            response = gson.fromJson(responseCodeAndBody.getBody(), Response.class);
	            
	            System.out.println("this is response::");
	            System.out.println(response.getMain().getTemp_min());
	            //response=obj.getMain().getTemp_min();
	        } catch (IOException e) {
	            throw new RuntimeException("IO exception during invocation of REST call");
	        }
	        return response;
	}

}
