package com.lobo.autentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lobo.autentication.entity.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
    Credential findByUserName(String userName);
    
}