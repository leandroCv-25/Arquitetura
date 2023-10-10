package com.lobo.autentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.lobo.autentication.dto.ResponseDTO;

@Component
public class ResponseService {
     @Autowired
    private RestTemplate restTemplate;

    public ResponseDTO auth (long sessionId, long userId){
        return restTemplate.postForObject(
            "http://127.0.0.1:8080/auth?sessionId="+sessionId+"&userId="+userId,null, ResponseDTO.class);
    }
}
