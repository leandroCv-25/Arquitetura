package com.lobo.autentication.utils;

import com.lobo.autentication.entity.Credential;

import java.time.Instant;
import java.util.Date;

import com.lobo.autentication.dto.CredentialDTO;
import com.lobo.autentication.entity.Access;


public class InstanceGenerator {

    private InstanceGenerator() {

    }

    public static Credential getCredential(String user) {
        Credential c = new Credential();

        c.setUserName(user);
        c.setPassword(user);

        return c;
    }

    public static CredentialDTO getCredentialDto(String userName) {
        CredentialDTO c = new CredentialDTO();

        c.setUserName(userName);
        c.setPassword(userName);

        return c;
    }

    public static Access getAcess() {
        Access ac = new Access();

        ac.setDate(Date.from(Instant.now()));

        return ac;
    }
} 
