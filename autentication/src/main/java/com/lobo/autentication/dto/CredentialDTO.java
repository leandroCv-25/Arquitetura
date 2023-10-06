package com.lobo.autentication.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CredentialDTO {
    private String userName;
    private String password;

    public CredentialDTO() {
    }

    public CredentialDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
