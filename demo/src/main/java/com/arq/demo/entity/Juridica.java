package com.arq.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Prof. Dr. Frank J. Affonso
 */
@Entity
@Table(name = "PessoaJuridica")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true, includeFieldNames = true)
public class Juridica extends Pessoa {

    private static final long serialVersionUID = 1L;

    @Column(name = "cnpj", unique = true)
    private String cnpj;

    public Juridica() {
    }
}
