package com.lobo.autentication.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity(name = "Credential")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Credential implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "credential_user", unique = true)
    private String userName;

    @Column(name = "credential_password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoa_idpessoa")
    private List<Access> access;

    public Credential() {
         this.access = new ArrayList<>();
    }

    public void setAccess(Access access) {
        this.access.add(access);
    }
}