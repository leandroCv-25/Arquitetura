package com.lobo.autentication.entity.mapper;

import com.lobo.autentication.entity.Credential;

public class CredentialMapper {
    private CredentialMapper() {
    }

    public static void update(Credential credentialUpdate, Credential newCredential) {

        credentialUpdate.setPassword(newCredential.getPassword());
    }
}
