package com.lobo.autentication.entity.mapper;

import com.lobo.autentication.entity.Access;
import com.lobo.autentication.entity.Credential;

public class CredentialMapper {
    private CredentialMapper() {
    }

    public static void update(Credential credentialUpdate, Credential newCredential) {

        for (Access ac : newCredential.getAccess()) {
           credentialUpdate.setAccess(ac);
        }
    }



}
