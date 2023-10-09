package com.lobo.autentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.lobo.autentication.dto.ResponseDTO;

public class ResponseService {
     @Autowired
    private RestTemplate restTemplate;

    public ResponseDTO auth (long id){
        return restTemplate.postForObject(
            "http://127.0.0.1:8080/auth?key="+id,null, ResponseDTO.class);
    }
}
