package br.com.service;

import br.com.DAO.TipoLogradouroDAO;
import br.com.model.TipoLogradouro;
import br.com.util.JWTUtil;

import java.util.List;

import static br.com.shared.constantes.Constantes.urlApi.tipoLogradouro.urlListarTipoLogradouro;

public class TipoLogradouroService {
    private TipoLogradouroDAO tipoLogradouroDAO;

    public TipoLogradouroService() {
        this.tipoLogradouroDAO = new TipoLogradouroDAO();
    }

    public List<TipoLogradouro> listar(){
        return this.tipoLogradouroDAO.listar();
    }

}
