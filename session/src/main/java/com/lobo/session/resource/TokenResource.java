package com.lobo.session.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lobo.session.dto.ResponseDTO;
import com.lobo.session.entity.Token;
import com.lobo.session.service.TokenService;
import com.lobo.session.utils.JwtTokenUtil;

@RestController
public class TokenResource {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private JwtTokenUtil JwtTokenUtil;

    @PostMapping("/signout")
    public ResponseEntity<ResponseDTO> logout(@RequestParam(value = "key") Long key) {
        ResponseDTO response = new ResponseDTO();
        if (key != null) {
            tokenService.delete(key);
            response.setMsg("Saiu do sistema");
            response.setSucess(true);
            return ResponseEntity.ok(response);
        } else {
            response.setMsg("Erro no formato da requisição");
            response.setSucess(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/check")
    public ResponseEntity<ResponseDTO> check(@RequestParam(value = "key") Long key) {
        ResponseDTO response = new ResponseDTO();
        if (key != null) {
            Token token = tokenService.findByKey(key);

            if (token != null) {
                Boolean isValid = JwtTokenUtil.validateToken(token.getJwtToken(), key);

                if (isValid) {
                    token.setJwtToken(JwtTokenUtil.generateToken(key));
                    tokenService.save(key, token);
                    response.setSucess(true);
                } else {
                    response.setMsg("Sessão Expirada");
                    response.setSucess(false);
                }
                return ResponseEntity.ok(response);
            } else {
                response.setMsg("Saiu do sistema");
                response.setSucess(false);
                return ResponseEntity.ok(response);
            }
        } else {
            response.setMsg("Erro no formato da requisição");
            response.setSucess(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<ResponseDTO> auth(@RequestParam(value = "key") Long key) {
        ResponseDTO response = new ResponseDTO();
        if (key != null) {
            Token token = new Token();
            token.setJwtToken(JwtTokenUtil.generateToken(key));
            tokenService.save(key, token);
            response.setSucess(true);
            response.setKey(key);
            return ResponseEntity.ok(response);
        } else{
            response.setMsg("Erro no formato da requisição");
            response.setSucess(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
