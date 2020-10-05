package br.com.service;

import br.com.model.TipoLogradouro;
import br.com.util.JWTUtil;

import java.util.List;

import static br.com.shared.constantes.Constantes.urlApi.tipoLogradouro.urlListarTipoLogradouro;

public class TipoLogradouroService {

    public List<TipoLogradouro> listar(){
        return JWTUtil.getInstance().chamarMetodoGetListagem(urlListarTipoLogradouro, TipoLogradouro[].class);
    }

}
