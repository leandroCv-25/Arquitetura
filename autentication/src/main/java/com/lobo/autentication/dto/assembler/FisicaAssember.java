package com.lobo.autentication.dto.assembler;

import com.lobo.autentication.dto.ContatoDTO;
import com.lobo.autentication.dto.EnderecoDTO;
import com.lobo.autentication.dto.FisicaDTO;
import com.lobo.autentication.entity.Acesso;
import com.lobo.autentication.entity.Contato;
import com.lobo.autentication.entity.Endereco;
import com.lobo.autentication.entity.Fisica;

public class FisicaAssember {
    private FisicaAssember() {

    }

    public static Fisica dtoToEntityModel(FisicaDTO dto) {
        Fisica fisica = new Fisica();

        fisica.setCpf(dto.getCpf());
        fisica.setDataNascimento(dto.getDataNascimento());
        fisica.setNome(dto.getNome());

        Acesso a = new Acesso();
        a.setUsuario(dto.getUsuario());
        a.setSenha(dto.getSenha());
        fisica.setAcesso(a);

        for (ContatoDTO cdto : dto.getContato()) {
            Contato c = new Contato();
            c.setTelefoneResidencial(cdto.getTelefoneResidencial());
            c.setTelefoneComercial(cdto.getTelefoneComercial());
            c.setCelular(cdto.getCelular());
            c.setEmail(cdto.getEmail());
            fisica.setContato(c);
        }

        for (EnderecoDTO edto : dto.getEndereco()) {
            Endereco e = new Endereco();
            e.setRua(edto.getRua());
            e.setNumero(edto.getNumero());
            e.setBairro(edto.getBairro());
            e.setCep(edto.getCep());
            e.setCidade(edto.getCidade());
            e.setEstado(edto.getEstado());
            fisica.setEndereco(e);
        }

        return fisica;
    }
}
