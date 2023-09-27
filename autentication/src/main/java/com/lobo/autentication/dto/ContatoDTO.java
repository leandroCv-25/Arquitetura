package com.lobo.autentication.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Prof. Dr. Frank J. Affonso
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ContatoDTO {

    private String telefoneResidencial;
    private String telefoneComercial;
    private String celular;
    private String email;

    public ContatoDTO() {
    }

    public ContatoDTO(String telefoneResidencial, String telefoneComercial, String celular,
            String email) {
        this.telefoneResidencial = telefoneResidencial;
        this.telefoneComercial = telefoneComercial;
        this.celular = celular;
        this.email = email;
    }
}