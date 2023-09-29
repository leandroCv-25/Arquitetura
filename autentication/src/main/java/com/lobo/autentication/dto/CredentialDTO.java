package com.lobo.autentication.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    private String nome;
    private String cpf;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date dataNascimento;

    public CredentialDTO() {
    }

    public CredentialDTO(String usuario, String senha) {
        this.userName = usuario;
        this.password = senha;
    }
}
