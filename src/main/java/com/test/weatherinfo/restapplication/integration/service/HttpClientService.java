package com.test.weatherinfo.restapplication.integration.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.test.weatherinfo.restapplication.model.ResponseCodeAndBody;


@Service
public class HttpClientService {


	public ResponseCodeAndBody get(String Url) throws IOException {
        URL url = new URL(Url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        ResponseCodeAndBody responseCodeAndBody = new ResponseCodeAndBody();
        responseCodeAndBody.setResponseCode(conn.getResponseCode());
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            StringBuilder body = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                body.append(output);
            }
            conn.disconnect();


            responseCodeAndBody.setBody(body.toString());
        } else {
            throw new  RuntimeException("The service at " + Url + " returned with code" + conn.getResponseCode());
        }
        return responseCodeAndBody;
    }
	
}
