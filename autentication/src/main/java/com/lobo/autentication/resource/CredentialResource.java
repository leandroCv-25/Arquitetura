package com.lobo.autentication.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lobo.autentication.dto.CredentialDTO;
import com.lobo.autentication.dto.assembler.CredentialAssember;
import com.lobo.autentication.entity.Credential;
import com.lobo.autentication.service.CredentialService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/auth")
public class CredentialResource {

    @Autowired
    private CredentialService credentialService;
    @Autowired
    private PasswordEncoder encoder;

    @Operation(summary = "Faz o login do usuário", description = "Retorna id de acesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Credencial válidas!", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "400", description = "Formato da requisição inválida!", content = @Content),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas!", content = @Content)
    })
    @PostMapping("/signin")
    public ResponseEntity<Boolean> signIn(@RequestBody CredentialDTO credentialDTO) {

        Credential credential = CredentialAssember.dtoToEntityModel(credentialDTO);
        if (credential != null) {
            Credential user = credentialService.findByUserName(credential.getUserName());

            if (user != null) {

                if (encoder.matches(credential.getPassword(), user.getPassword())) {
                    return ResponseEntity.ok(true);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signUp(@RequestBody CredentialDTO credentialDTO) {

        Credential credential = CredentialAssember.dtoToEntityModel(credentialDTO);
        if (credential != null) {
            Credential user = credentialService.findByUserName(credential.getUserName());

            if (user == null) {
                credential.setPassword(encoder.encode(credential.getPassword()));

                Credential credentialInsert = credentialService.save(credential);
                if (credentialInsert != null) {
                    return ResponseEntity.ok(true);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
                }
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

    // @PutMapping("/")
    // public boolean update(@RequestBody CredentialDTO credentialDto) {
    //     boolean update = false;

    //     Credential newCredential = CredentialAssember.dtoToEntityModel(credentialDto);
    //     Credential credentialUpdate = credentialService.findByUserName(newCredential.getUserName());

    //     CredentialMapper.update(credentialUpdate, newCredential);

    //     Credential credentialUpdated = credentialService.update(credentialUpdate);
    //     if (credentialUpdated != null) {
    //         update = true;
    //     }

    //     return update;
    // }
}
