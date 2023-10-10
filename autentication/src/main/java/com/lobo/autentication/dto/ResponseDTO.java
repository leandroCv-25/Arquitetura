package com.lobo.autentication.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResponseDTO {
    private boolean sucess;
    private String msg;
    private long key;
    private long id;

    public ResponseDTO() {
    }

    public ResponseDTO(String msg, boolean sucess, long key, long id) {
        this.msg = msg;
        this.sucess = sucess;
        this.key = key;
        this.id = id;
    }
}
