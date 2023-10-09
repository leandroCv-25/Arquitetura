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
import com.lobo.autentication.dto.ResponseDTO;
import com.lobo.autentication.dto.assembler.CredentialAssember;
import com.lobo.autentication.entity.Credential;
import com.lobo.autentication.entity.mapper.CredentialMapper;
import com.lobo.autentication.service.CredentialService;
import com.lobo.autentication.service.ResponseService;

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
    private ResponseService responseService;
    @Autowired
    private PasswordEncoder encoder;

    @Operation(summary = "Faz o login do usuário", description = "Retorna resposta com id de acesso caso for um sucesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realizando o processo de autenticação.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Formato da requisição inválida!", content = @Content),
    })
    @PostMapping("/signin")
    public ResponseEntity<ResponseDTO> signIn(@RequestBody CredentialDTO credentialDTO) {
        ResponseDTO response = new ResponseDTO();

        Credential credential = CredentialAssember.dtoToEntityModel(credentialDTO);
        if (credential != null) {
            Credential user = credentialService.findByUserName(credential.getUserName());

            if (user != null) {

                if (encoder.matches(credential.getPassword(), user.getPassword())) {

                    CredentialMapper.update(user, credential);

                    ResponseDTO restResponseDTO = responseService
                            .auth(user.getAccess().get(user.getAccess().size() - 1).getIdAccess());

                    if (restResponseDTO.isSucess()) {
                        response.setMsg("Logado no sistema");
                        response.setSucess(true);
                        response.setKey(user.getId());
                    } else {
                        response.setMsg("Erro nosso, espere um momento");
                        response.setSucess(false);
                        response.setKey(user.getId());
                    }
                    return ResponseEntity.ok(response);

                } else {
                    response.setMsg("Credenciais inválidas");
                    response.setSucess(false);
                    return ResponseEntity.ok(response);
                }
            } else {
                response.setMsg("Credenciais inválidas");
                response.setSucess(false);
                return ResponseEntity.ok(response);
            }
        } else {
            response.setMsg("Erro no formato da requisição");
            response.setSucess(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

    @Operation(summary = "Faz o cadrastro do usuário", description = "Retorna resposta com id de acesso caso for um sucesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realizando o processo de criação de acesso.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Formato da requisição inválida!", content = @Content),
    })
    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signUp(@RequestBody CredentialDTO credentialDTO) {
        ResponseDTO response = new ResponseDTO();
        Credential credential = CredentialAssember.dtoToEntityModel(credentialDTO);
        if (credential != null) {
            Credential user = credentialService.findByUserName(credential.getUserName());

            if (user == null) {
                credential.setPassword(encoder.encode(credential.getPassword()));

                Credential credentialInsert = credentialService.save(credential);
                if (credentialInsert != null) {

                    ResponseDTO restResponseDTO = responseService
                            .auth(credentialInsert.getAccess().get(credentialInsert.getAccess().size() - 1)
                                    .getIdAccess());

                    if (restResponseDTO.isSucess()) {
                        response.setMsg("Logado no sistema");
                        response.setSucess(true);
                        response.setKey(credentialInsert.getId());
                    } else {
                        response.setMsg("Credencial salva");
                        response.setSucess(false);
                        response.setKey(credentialInsert.getId());
                    }
                    return ResponseEntity.ok(response);
                } else {
                    response.setMsg("Erro nosso, espere um momento");
                    response.setSucess(false);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                }
            } else {
                response.setMsg("Credencial de login não disponível");
                response.setSucess(false);
                return ResponseEntity.ok(response);
            }
        } else {
            response.setMsg("Erro no formato da requisição");
            response.setSucess(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
