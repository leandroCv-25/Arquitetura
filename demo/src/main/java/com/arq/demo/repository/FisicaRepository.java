package com.arq.demo.repository;

import com.arq.demo.entity.Fisica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FisicaRepository extends JpaRepository<Fisica, Long> {

    Fisica findByCpf(String cpf);
}
