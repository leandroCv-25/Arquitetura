package com.lobo.session.repository;

import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.lobo.session.entity.Token;

@Component
public class TokenRepository {
    @Autowired
    private HazelcastInstance hazelcastInstance;

    public TokenRepository() {

    }

    private ConcurrentMap<String, Token> retrieveMap() {
        return hazelcastInstance.getMap("tokens");
    }

    public Token save(long key,Token token) {
        return retrieveMap().put(Long.toString(key), token);
    }

    public Token findByKey(long key) {
        return retrieveMap().get(Long.toString(key));
    }

    public void delete(long key) {
        retrieveMap().remove(Long.toString(key));
    }

}
