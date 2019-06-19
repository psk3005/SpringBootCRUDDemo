package com.praveen.springboot.example.demo.client;

import com.praveen.springboot.example.demo.data.Weather;
import com.praveen.springboot.example.demo.exception.WeatherErrorResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherClient {

    private static Logger logger = LoggerFactory.getLogger(WeatherClient.class);

    @Autowired
    private RestTemplate restTemplate;

    public Weather getWeather(){
        ResponseEntity<Weather> responseEntity;
        String restUrl = "https://localhost:8443/weather";
        logger.info("Sending request to: {}", restUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Weather> requestEntity = new HttpEntity<>(httpHeaders);
        try {
            responseEntity = restTemplate.exchange(restUrl, HttpMethod.GET, requestEntity, Weather.class);
        } catch (HttpStatusCodeException e) {
            throw new WeatherErrorResponseException("Error response returned by the Weather REST Service", e);
        }
        logger.info("Response entity body received: {}", responseEntity.getBody());
        return responseEntity.getBody();
    }
}
