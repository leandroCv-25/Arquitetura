package com.lobo.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.lobo.autentication.ApiApplication;
import com.lobo.autentication.dto.CredentialDTO;
import com.lobo.autentication.dto.ResponseDTO;
import com.lobo.autentication.resource.CredentialResource;
import com.lobo.autentication.utils.InstanceGenerator;


@SpringBootTest(classes = ApiApplication.class)
public class credentialResourceTest {

    private CredentialDTO entity;

    @Autowired(required = true)
    private CredentialResource cr = new CredentialResource();

    // @Disabled
    @Test
    @DisplayName("CredentialResource.save(Credential)")
    void testSignUp() {
        entity = InstanceGenerator.getCredentialDto("user5");
        System.out.println(entity);

        ResponseEntity<ResponseDTO> f = cr.signIn(entity);
        System.out.println("----------------------------------------");
        System.out.println(f.getBody().isSucess());
        System.out.println("----------------------------------------");

        assertEquals(true, f.getBody().isSucess());
    }

    @Test
    @DisplayName("CredentialResource.save(Credential)")
    void testSignIn() {
        entity = InstanceGenerator.getCredentialDto("user5");
        System.out.println(entity);

        ResponseEntity<ResponseDTO> f = cr.signIn(entity);
        System.out.println("----------------------------------------");
        System.out.println(f.getBody().isSucess());
        System.out.println("----------------------------------------");

        assertEquals(true, f.getBody().isSucess());
    }

    
}
