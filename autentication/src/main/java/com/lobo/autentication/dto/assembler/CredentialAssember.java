package com.lobo.autentication.dto.assembler;

import java.time.Instant;
import java.util.Date;

import com.lobo.autentication.dto.CredentialDTO;
import com.lobo.autentication.entity.Access;
import com.lobo.autentication.entity.Credential;

public class CredentialAssember {
    private CredentialAssember() {

    }

    public static Credential dtoToEntityModel(CredentialDTO dto) {
        Credential credential = new Credential();

        credential.setUserName(dto.getCpf());
        credential.setPassword(dto.getPassword());
        Access access = new Access();
        access.setDate(Date.from(Instant.now()));
        credential.setAccess(access);

        return credential;
    }
}
