package br.com.service;

import br.com.model.UnidadeFederativa;
import br.com.util.JWTUtil;

import java.util.List;

import static br.com.shared.constantes.Constantes.urlApi.unidadeFederativa.urlListarUnidadeFederativa;

public class UnidadeFederativaService {

    public List<UnidadeFederativa> listar() {
        return JWTUtil.getInstance().chamarMetodoGetListagem(urlListarUnidadeFederativa, UnidadeFederativa[].class);
    }

}
