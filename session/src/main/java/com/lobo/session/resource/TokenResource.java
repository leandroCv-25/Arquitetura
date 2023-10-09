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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class TokenResource {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private JwtTokenUtil JwtTokenUtil;

    @Operation(summary = "Faz o logout do usuário", description = "Retorna resposta com o resultado em boolean e mensagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realizando o logout do usuario.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Formato da requisição inválida!", content = @Content),
    })
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

    @Operation(summary = "Avalia se a sessão ainda é valída do usuário", description = "Retorna resposta com o resultado em boolean e mensagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realizando a validação do usuario.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Formato da requisição inválida!", content = @Content),
    })
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

    @Operation(summary = "Armazena a sessão do usuário", description = "Retorna resposta com o resultado em boolean e mensagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Armazenando a sessão do usuario.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Formato da requisição inválida!", content = @Content),
    })
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
        } else {
            response.setMsg("Erro no formato da requisição");
            response.setSucess(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
