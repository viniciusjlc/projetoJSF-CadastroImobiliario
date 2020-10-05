package br.com.service;

import br.com.model.CadastroImobiliario;
import br.com.util.JWTUtil;

import java.util.List;

import static br.com.shared.constantes.Constantes.urlApi.cadatroImobiliario.*;

public class CadastroImobiliarioService {
    public List<CadastroImobiliario> listar() {
        return JWTUtil.getInstance().chamarMetodoGetListagem(urlListarCadastroImobiliario, CadastroImobiliario[].class);
    }

    public List<CadastroImobiliario> consultarImobiliarioPorUsuario(Integer idUsuario) {
        return JWTUtil.getInstance().chamarMetodoGetListagem(urlConsultarCadastroImobiliarioPorUsuario + idUsuario, CadastroImobiliario[].class);
    }

    public CadastroImobiliario cadastrar(CadastroImobiliario cadastroImobiliario) {
        return JWTUtil.getInstance().chamarMetodoPost(urlSalvarCadastroImobiliario, cadastroImobiliario, CadastroImobiliario.class);
    }

    public CadastroImobiliario excluir(Integer idCadastroImobiliario) {
        return JWTUtil.getInstance().chamarMetodoDelete(urlExcluirCadastroImobiliario + idCadastroImobiliario, CadastroImobiliario.class);
    }
}