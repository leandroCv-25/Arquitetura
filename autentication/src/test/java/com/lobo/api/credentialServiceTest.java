package com.lobo.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lobo.autentication.entity.Credential;
import com.lobo.autentication.service.CredentialService;
import com.lobo.autentication.utils.InstanceGenerator;

@SpringBootTest
public class credentialServiceTest {

    private Credential entity;

    @Autowired(required = true)
    private CredentialService cs = new CredentialService();

    // @Disabled
    @Test
    @DisplayName("CredentialService.save(Credential)")
    void testSave() {
        entity = InstanceGenerator.getCredential("user2");
        System.out.println(entity);

        Credential f = cs.save(entity);
        System.out.println("----------------------------------------");
        System.out.println(f);
        System.out.println("----------------------------------------");

        assertEquals(entity, f);
    }

    // @Disabled
    @Test
    @DisplayName("CredentialService.findByCpf(cpf)")
    void testFindByCpf() {
        entity = InstanceGenerator.getCredential("user1");

        String cpf = "user1";
        Credential f = cs.findByUserNameCredential(cpf);
        System.out.println("----------------------------------------");
        System.out.println(f);
        System.out.println("----------------------------------------");

        assertEquals(entity, f);
    }

    // @Disabled
    @Test
    public void testDelete() {
        entity = InstanceGenerator.getCredential("user1");

        cs.delete(entity);

        String userName = "user1";
        Credential d = cs.findByUserNameCredential(userName);

        assertNotEquals(entity, d);
    }

    // @Disabled
    @Test
    public void testUpdate() {
        entity = InstanceGenerator.getCredential("user1");

        entity.setPassword("user2");

        cs.update(entity);

        String userName = "user1";
        Credential d = cs.findByUserNameCredential(userName);

        assertEquals(entity, d);
    }

    // @Disabled
    @Test
    @DisplayName("CredentialService.findAll()")
    public void testFindAll() {
        System.out.println("findAll");

        Credential expResult = null;
        List<Credential> result = cs.findAll();
        for (Credential f : result) {
            System.out.println("FISICA: " + f);
        }
        assertNotEquals(expResult, result);
    }
}
