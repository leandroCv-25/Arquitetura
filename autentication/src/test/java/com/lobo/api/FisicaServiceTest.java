package com.lobo.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lobo.autentication.entity.Fisica;
import com.lobo.autentication.service.FisicaService;
import com.lobo.autentication.utils.InstanceGenerator;

@SpringBootTest
public class FisicaServiceTest {
    
    private Fisica entity;

    @Autowired(required = true)
    private FisicaService fs = new FisicaService();
    
    // @Disabled
    @Test
    @DisplayName("FisicaService.save(Fisica)")
    void testSave() {
        entity = InstanceGenerator.getPessoaFisica("222.333.444-56", "user2");
        System.out.println(entity);

        Fisica f = fs.save(entity);
        System.out.println("----------------------------------------");
        System.out.println(f);
        System.out.println("----------------------------------------");

        assertEquals(entity, f);
    }

    // @Disabled
    @Test
    @DisplayName("FisicaService.findByCpf(cpf)")
    void testFindByCpf() {
        entity = InstanceGenerator.getPessoaFisica("222.333.444-55", "user1");

        String cpf = "222.333.444-55";
        Fisica f = fs.findByCpf(cpf);
        System.out.println("----------------------------------------");
        System.out.println(f);
        System.out.println("----------------------------------------");

        assertEquals(entity, f);
    }

    // @Disabled
    @Test
    public void testDelete() {
        entity = InstanceGenerator.getPessoaFisica("222.333.444-55", "user1");
        
        fs.delete(entity); 

        String cpf = "222.333.444-55";
        Fisica d = fs.findByCpf(cpf);

        assertNotEquals(entity,d);
    }

    @Disabled
    @Test
    public void testUpdate() {
        //...
    }

    // @Disabled
    @Test
    @DisplayName("FisicaService.findAll()")
    public void testFindAll() {
        System.out.println("findAll");

        Fisica expResult = null;
        List<Fisica> result = fs.findAll();
        for (Fisica f : result){
            System.out.println("FISICA: " + f);
        }
        assertNotEquals(expResult, result);
    }
}
