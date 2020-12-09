package br.com.service;

import br.com.DAO.CadastroImobiliarioDAO;
import br.com.model.CadastroImobiliario;

import java.util.List;

public class CadastroImobiliarioService {
    private CadastroImobiliarioDAO cadastroImobiliarioDAO;

    public CadastroImobiliarioService() {
        this.cadastroImobiliarioDAO = new CadastroImobiliarioDAO();
    }
    public List<CadastroImobiliario> consultarImobiliarioPorUsuario(Integer idUsuario) {
        return cadastroImobiliarioDAO.consultarImobiliarioPorUsuario(idUsuario);
    }

    public void cadastrarAlterar(CadastroImobiliario cadastroImobiliario) {
        if (cadastroImobiliario.getId() != null) {
            cadastroImobiliarioDAO.cadastrar(cadastroImobiliario);
        } else {
            cadastroImobiliarioDAO.alterar(cadastroImobiliario);
        }
    }

    public void excluir(Integer idCadastroImobiliario) {
        cadastroImobiliarioDAO.excluir(idCadastroImobiliario);
    }
}