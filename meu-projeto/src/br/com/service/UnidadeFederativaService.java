package br.com.service;

import br.com.DAO.UnidadeFederativaDAO;
import br.com.model.UnidadeFederativa;
import br.com.util.JWTUtil;

import java.util.List;

import static br.com.shared.constantes.Constantes.urlApi.unidadeFederativa.urlListarUnidadeFederativa;

public class UnidadeFederativaService {
    private UnidadeFederativaDAO unidadeFederativaDAO;

    public UnidadeFederativaService() {
        this.unidadeFederativaDAO = new UnidadeFederativaDAO();
    }

    public List<UnidadeFederativa> listar() {
        return this.unidadeFederativaDAO.listar();
    }

}
