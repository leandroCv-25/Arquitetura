package com.arq.demo;

import com.arq.demo.entity.Fisica;
import com.arq.demo.utils.InstanceGenerator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 *
 * @author Prof. Dr. Frank J. Affonso
 */
@SpringBootApplication
public class TutorialApplication implements CommandLineRunner{
    
    public static void main(String[] args) {
        SpringApplication.run(TutorialApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Fisica entity = InstanceGenerator.getPessoaFisica("111.222.333-44", "user1");
        System.out.println("\n" + entity + "\n");
    }
}
