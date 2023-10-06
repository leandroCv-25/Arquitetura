package com.lobo.session.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Boolean> logout(@RequestParam(value = "key") long key) {
        tokenService.delete(key);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> check(@RequestParam(value = "key") Long key) {
        if (key != null) {
            Token token = tokenService.findByKey(key);

            if (token != null) {
                Boolean isValid = JwtTokenUtil.validateToken(token.getJwtToken(), key);

                if (isValid) {
                    token.setJwtToken(JwtTokenUtil.generateToken(key));
                    tokenService.save(key, token);
                }
                return ResponseEntity.ok(isValid);
            } else {
                return ResponseEntity.ok(false);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<Boolean> auth(@RequestParam(value = "key") long key) {
        Token token = new Token();
        token.setJwtToken(JwtTokenUtil.generateToken(key));
        tokenService.save(key, token);
        return ResponseEntity.ok(true);
    }

}
