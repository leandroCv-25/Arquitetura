package com.lobo.autentication.entity.mapper;

import com.lobo.autentication.entity.Acesso;
import com.lobo.autentication.entity.Contato;
import com.lobo.autentication.entity.Endereco;
import com.lobo.autentication.entity.Fisica;

public class FisicaMapper {
    private FisicaMapper() {
    }

    public static void update(Fisica fisicaUpdate, Fisica newFisica) {

        fisicaUpdate.setDataNascimento(newFisica.getDataNascimento());
        fisicaUpdate.setNome(newFisica.getNome());

        Acesso a = fisicaUpdate.getAcesso();
        a.setUsuario(newFisica.getAcesso().getUsuario());
        a.setSenha(newFisica.getAcesso().getSenha());
        fisicaUpdate.setAcesso(a);

        int i = 0;
        for (Endereco e : fisicaUpdate.getEndereco()) {
            Endereco edto = newFisica.getEndereco().get(i);
            e.setRua(edto.getRua());
            e.setNumero(edto.getNumero());
            e.setBairro(edto.getBairro());
            e.setCep(edto.getCep());
            e.setCidade(edto.getCidade());
            e.setEstado(edto.getEstado());
            i++;
        }

        if (fisicaUpdate.getEndereco().size() < newFisica.getEndereco().size()) {
            for (int index = i; index<newFisica.getEndereco().size();index++) {
                fisicaUpdate.getEndereco().add(newFisica.getEndereco().get(index));
            }
        }

        int j = 0;
        for (Contato c : fisicaUpdate.getContato()) {
            Contato cdto = newFisica.getContato().get(j);
            c.setTelefoneResidencial(cdto.getTelefoneResidencial());
            c.setTelefoneComercial(cdto.getTelefoneComercial());
            c.setCelular(cdto.getCelular());
            c.setEmail(cdto.getEmail());
            j++;
        }

        if (fisicaUpdate.getContato().size() < newFisica.getContato().size()) {
            for (int index = j; index<newFisica.getContato().size();index++) {
                fisicaUpdate.getContato().add(newFisica.getContato().get(index));
            }
        }

    }
}
