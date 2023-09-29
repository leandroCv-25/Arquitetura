package com.lobo.autentication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lobo.autentication.entity.Credential;
import com.lobo.autentication.repository.CredentialRepository;

@Component
public class CredentialService {

     @Autowired
    private CredentialRepository repository;

    public CredentialService() {
    }

    public Credential save(Credential entity) {
        Credential persistedEntity = null;

        if (repository != null) {
            persistedEntity = repository.save(entity);
        }

        return persistedEntity;
    }

    public Credential findByUserNameCredential(String userName) {
        Credential insertedEntity = null;

        if (repository != null) {
            insertedEntity = repository.findByUserNameCredential(userName);
        }

        return insertedEntity;
    }

    public void delete(Credential entity) {

        if (repository != null) {
            repository.delete(entity);
        }
    }

    public Credential update(Credential entity) {

        Credential persistedEntity = null;

        if (repository != null) {
            persistedEntity = repository.save(entity);
        }

        return persistedEntity;
    }

    public List<Credential> findAll() {
        List<Credential> list = null;

        if (repository != null) {
            list = new ArrayList<>();
            list = repository.findAll();
        }

        return list;
    }
}
