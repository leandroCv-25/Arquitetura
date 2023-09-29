package com.lobo.autentication.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lobo.autentication.dto.CredentialDTO;
import com.lobo.autentication.dto.assembler.CredentialAssember;
import com.lobo.autentication.entity.Credential;
import com.lobo.autentication.entity.mapper.CredentialMapper;
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


    @Operation(summary = "Faz o login do usuário", description = "Retorna id de acesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa-física encontrada!", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Credential.class))
            }),
            @ApiResponse(responseCode = "400", description = "CPF inválido!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pessoa-física não encontrada!", content = @Content)
    })
    @GetMapping("/cpf/")
    public Credential getCredentialByCpf(@RequestParam(value = "cpf") String cpf) {
        Credential credential = credentialService.findByUserNameCredential(cpf);

        return credential;
    }

    @DeleteMapping("/{cpf}")
    public boolean delete(@PathVariable(value = "cpf") String cpf) {
        boolean delete = false;
        Credential credentialDelete = credentialService.findByUserNameCredential(cpf);

        if (credentialDelete != null) {
            credentialService.delete(credentialDelete);
            delete = true;
        }

        return delete;
    }

    @PostMapping("/")
    public boolean saveCredential(@RequestBody CredentialDTO credentialDto) {
        boolean insert = false;

        Credential credential = CredentialAssember.dtoToEntityModel(credentialDto);
        Credential credentialInsert = credentialService.save(credential);
        if (credentialInsert != null) {
            insert = true;
        }

        return insert;
    }

    @PutMapping("/")
    public boolean update(@RequestBody CredentialDTO credentialDto) {
        boolean update = false;

        Credential newCredential = CredentialAssember.dtoToEntityModel(credentialDto);
        Credential credentialUpdate = credentialService.findByUserNameCredential(newCredential.getUserName());

        CredentialMapper.update(credentialUpdate, newCredential);

        Credential credentialUpdated = credentialService.update(credentialUpdate);
        if (credentialUpdated != null) {
            update = true;
        }

        return update;
    }
}
