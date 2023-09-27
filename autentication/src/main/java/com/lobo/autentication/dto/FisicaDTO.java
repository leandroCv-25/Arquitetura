package com.lobo.autentication.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FisicaDTO {
    private String usuario;
    private String senha;
    private String nome;
    private String cpf;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date dataNascimento;
    private List<EnderecoDTO> endereco;
    private List<ContatoDTO> contato;

    public FisicaDTO() {
        this.endereco = new ArrayList<>();
        this.contato = new ArrayList<>();
    }

    public FisicaDTO(String usuario, String senha, String nome, String cpf, Date dataNascimento,
            List<EnderecoDTO> endereco, List<ContatoDTO> contato) {
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.contato = contato;
    }
}
