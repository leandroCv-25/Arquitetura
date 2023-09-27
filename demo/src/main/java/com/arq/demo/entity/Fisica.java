package com.arq.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Prof. Dr. Frank J. Affonso
 */
@Entity
@Table(name = "PessoaFisica")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, exclude = {"dataNascimento"})
@ToString(callSuper = true, includeFieldNames = true)
public class Fisica extends Pessoa {

    private static final long serialVersionUID = 1L;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;

    public Fisica() {
    }    
}
