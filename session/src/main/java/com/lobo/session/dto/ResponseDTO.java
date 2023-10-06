package com.lobo.session.dto;

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

    public ResponseDTO() {
    }

    public ResponseDTO(String msg, boolean sucess, long key) {
        this.msg = msg;
        this.sucess = sucess;
        this.key = key;
    }
}
