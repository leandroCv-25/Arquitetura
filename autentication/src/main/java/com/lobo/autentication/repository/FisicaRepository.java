package com.lobo.autentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lobo.autentication.entity.Fisica;

public interface FisicaRepository extends JpaRepository<Fisica, Long> {

    Fisica findByCpf(String cpf);
}
