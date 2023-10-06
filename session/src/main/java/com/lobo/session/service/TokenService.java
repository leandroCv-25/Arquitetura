package com.lobo.session.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lobo.session.entity.Token;
import com.lobo.session.repository.TokenRepository;

@Component
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    public TokenService() {

    }

    public Token findByKey(long key) {
        Token persistedEntity = null;

        if (tokenRepository != null) {
            persistedEntity = tokenRepository.findByKey(key);
        }

        return persistedEntity;
    }

    public void delete(long key) {
        if (tokenRepository != null) {
            tokenRepository.delete(key);
        }
    }

    public Token save(long key, Token token) {
        Token persistedEntity = null;

        if (tokenRepository != null) {
            persistedEntity = tokenRepository.save(key, token);
        }

        return persistedEntity;
    }
}
